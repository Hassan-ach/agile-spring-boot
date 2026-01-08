package com.ensa.agile.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.ensa.agile",
                importOptions = DoNotIncludeTests.class)
public class LayerDependencyTest {

    @ArchTest
    static final ArchRule layers_should_respect_boundaries =
        layeredArchitecture()
            .consideringAllDependencies()
            .layer("Domain")
            .definedBy("..domain..")
            .layer("Application")
            .definedBy("..application..")
            .layer("Presentation")
            .definedBy("..presentation..")
            .layer("Infrastructure")
            .definedBy("..infrastructure..")
            .whereLayer("Domain")
            .mayOnlyBeAccessedByLayers("Application", "Infrastructure",
                                       "Presentation")
            .whereLayer("Application")
            .mayOnlyBeAccessedByLayers("Presentation", "Infrastructure")
            .whereLayer("Presentation")
            .mayNotBeAccessedByAnyLayer()
            .whereLayer("Infrastructure")
            .mayNotBeAccessedByAnyLayer();

    @ArchTest
    static final ArchRule domain_layer_isolation =
        classes()
            .that()
            .resideInAPackage("..domain..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..domain..", "java..", "..lombok..");

    @ArchTest
    static final ArchRule application_layer_isolation =
        classes()
            .that()
            .resideInAPackage("..application..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..application..", "..domain..", "java..",
                                "..lombok..",
                                "org.springframework.stereotype..");

    @ArchTest
    static final ArchRule presentation_layer_isolation =
        classes()
            .that()
            .resideInAPackage("..presentation..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage(
                "..presentation..", "..application..", "..domain..", "java..",
                "..lombok..", "org.springframework.http..",
                "org.springframework.web..", "org.springframework.security..");
}
