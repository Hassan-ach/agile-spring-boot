package com.ensa.agile.architecture;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.ensa.agile", importOptions = DoNotIncludeTests.class)
public class InfrastructureRulesTest {

    @ArchTest
    static final ArchRule repository_adapters_should_be_in_jpa_layer =
        classes().that().haveSimpleNameEndingWith("RepositoryAdapter")
            .should().resideInAPackage("..infrastructure.persistence.jpa..");

    @ArchTest
    static final ArchRule jpa_repositories_should_be_in_jpa_layer =
        classes().that().haveSimpleNameStartingWith("Jpa").and().haveSimpleNameEndingWith("Repository")
            .should().resideInAPackage("..infrastructure.persistence.jpa..");

    @ArchTest
    static final ArchRule jpa_entities_should_be_in_jpa_layer =
        classes().that().haveSimpleNameEndingWith("JpaEntity")
            .should().resideInAPackage("..infrastructure.persistence.jpa..");
}
