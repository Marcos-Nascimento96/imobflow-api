package com.marcos.imobflow.architecture;

import com.tngtech.archunit.core.domain.Dependency;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import jakarta.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AnalyzeClasses(
        packages = "com.marcos.imobflow",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class HexagonalArchitectureTest {

    private static final String BASE = "com.marcos.imobflow.";
    private static final String DOMAIN = BASE + "domain..";
    private static final String APPLICATION = BASE + "application..";
    private static final String APPLICATION_DTO = BASE + "application.dto..";
    private static final String APPLICATION_USECASE = BASE + "application.usecase..";
    private static final String ADAPTERS_IN = BASE + "adapters.in..";
    private static final String ADAPTERS_OUT = BASE + "adapters.out..";
    private static final String CONFIG = BASE + "config..";
    private static final String DOMAIN_REPOSITORY = BASE + "domain.repository..";
    private static final String SERVICE = "org.springframework.stereotype.Service";

    @ArchTest
    static void expected_production_packages_are_present(JavaClasses classes) {
        assertPackageIsNotEmpty(classes, BASE + "domain");
        assertPackageIsNotEmpty(classes, BASE + "application");
        assertPackageIsNotEmpty(classes, BASE + "adapters.in");
        assertPackageIsNotEmpty(classes, BASE + "adapters.out");
        assertPackageIsNotEmpty(classes, BASE + "domain.repository");

        long entities = classes.stream()
                .filter(javaClass -> javaClass.isAnnotatedWith(Entity.class))
                .count();
        assertTrue(entities > 0, "A análise arquitetural deve alcançar ao menos uma entidade JPA");
    }

    @ArchTest
    static final ArchRule domain_is_independent_from_frameworks_and_external_layers = classes()
            .that().resideInAPackage(DOMAIN)
            .should().onlyDependOnClassesThat().resideInAnyPackage("java..", DOMAIN)
            .because("o domínio deve permanecer puro e independente de frameworks e camadas externas");

    @ArchTest
    static final ArchRule application_does_not_depend_on_adapters_or_config = noClasses()
            .that().resideInAPackage(APPLICATION)
            .should().dependOnClassesThat().resideInAnyPackage(ADAPTERS_IN, ADAPTERS_OUT, CONFIG)
            .because("a aplicação deve acessar o exterior somente por portas do domínio");

    @ArchTest
    static final ArchRule application_does_not_depend_on_forbidden_framework_modules = noClasses()
            .that().resideInAPackage(APPLICATION)
            .should().dependOnClassesThat().resideInAnyPackage(
                    "org.springframework.web..",
                    "org.springframework.data..",
                    "org.springframework.security..",
                    "jakarta.persistence.."
            )
            .because("Spring Web, Data, Security e Jakarta Persistence pertencem às bordas técnicas");

    @ArchTest
    static final ArchRule application_framework_permissions_are_restricted = classes()
            .that().resideInAPackage(APPLICATION)
            .should(useOnlyApprovedApplicationDependencies())
            .because("a aplicação permite somente @Service nos casos de uso e Jakarta Validation nos DTOs");

    @ArchTest
    static final ArchRule input_adapters_do_not_depend_on_output_or_persistence = noClasses()
            .that().resideInAPackage(ADAPTERS_IN)
            .should().dependOnClassesThat().resideInAnyPackage(
                    ADAPTERS_OUT,
                    "org.springframework.data..",
                    "jakarta.persistence.."
            )
            .because("adaptadores de entrada devem delegar à aplicação, sem acessar persistência");

    @ArchTest
    static final ArchRule output_adapters_do_not_depend_on_input_adapters = noClasses()
            .that().resideInAPackage(ADAPTERS_OUT)
            .should().dependOnClassesThat().resideInAPackage(ADAPTERS_IN)
            .because("adaptadores de saída não devem conhecer os adaptadores de entrada");

    @ArchTest
    static final ArchRule jpa_entities_are_exclusive_to_output_adapters = classes()
            .that().areAnnotatedWith(Entity.class)
            .should().resideInAPackage(ADAPTERS_OUT)
            .because("entidades JPA são detalhes de persistência do adaptador de saída");

    @ArchTest
    static final ArchRule domain_repository_ports_are_interfaces = classes()
            .that().resideInAPackage(DOMAIN_REPOSITORY)
            .should().beInterfaces()
            .because("portas de repositório do domínio devem ser contratos abstratos");

    private static ArchCondition<JavaClass> useOnlyApprovedApplicationDependencies() {
        return new ArchCondition<>("usar somente dependências aprovadas para a camada application") {
            @Override
            public void check(JavaClass source, ConditionEvents events) {
                for (Dependency dependency : source.getDirectDependenciesFromSelf()) {
                    JavaClass target = dependency.getTargetClass();
                    if (!isApprovedApplicationDependency(source, target)) {
                        events.add(SimpleConditionEvent.violated(
                                dependency,
                                source.getName() + " depende de " + target.getName()
                                        + ", que não é permitido na camada application"
                        ));
                    }
                }
            }
        };
    }

    private static boolean isApprovedApplicationDependency(JavaClass source, JavaClass target) {
        String targetPackage = target.getPackageName();

        if (targetPackage.equals("java") || targetPackage.startsWith("java.")) {
            return true;
        }
        if (targetPackage.startsWith(BASE + "application") || targetPackage.startsWith(BASE + "domain")) {
            return true;
        }
        if (target.getName().equals(SERVICE)) {
            return source.getPackageName().startsWith(BASE + "application.usecase");
        }
        if (targetPackage.startsWith("jakarta.validation")) {
            return source.getPackageName().startsWith(BASE + "application.dto");
        }
        return false;
    }

    private static void assertPackageIsNotEmpty(JavaClasses classes, String packagePrefix) {
        boolean present = classes.stream()
                .anyMatch(javaClass -> javaClass.getPackageName().startsWith(packagePrefix));
        assertTrue(present, "Nenhuma classe de produção encontrada em " + packagePrefix);
    }
}
