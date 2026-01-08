package com.ensa.agile.architecture;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.ensa.agile", importOptions = DoNotIncludeTests.class)
public class MapperRulesTest {

    @ArchTest
    static final ArchRule application_mappers_should_be_in_mapper_package =
        classes().that().haveSimpleNameEndingWith("Mapper")
            .and().resideInAPackage("..application..")
            .and().resideOutsideOfPackages("..global..", "..common..")
            .should().resideInAPackage("..mapper..");

    @ArchTest
    static final ArchRule response_mappers_should_be_in_mapper_package =
        classes().that().haveSimpleNameEndingWith("ResponseMapper")
            .should().resideInAPackage("..mapper..");

    @ArchTest
    static final ArchRule request_mappers_should_be_in_mapper_package =
        classes().that().haveSimpleNameEndingWith("RequestMapper")
            .should().resideInAPackage("..mapper..");

    @ArchTest
    static final ArchRule jpa_mappers_must_be_in_jpa_package =
        classes().that().haveSimpleNameEndingWith("JpaMapper")
            .should().resideInAPackage("..infrastructure.persistence.jpa..");
}
