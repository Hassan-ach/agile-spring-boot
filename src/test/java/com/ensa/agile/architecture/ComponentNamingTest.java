package com.ensa.agile.architecture;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.ensa.agile", importOptions = DoNotIncludeTests.class)
public class ComponentNamingTest {

    @ArchTest
    static final ArchRule domain_repositories_should_be_in_repository_package =
        classes().that().haveSimpleNameEndingWith("Repository")
            .and().resideInAPackage("..domain..")
            .and().resideOutsideOfPackages("..global..", "..common..")
            .should().resideInAPackage("..repository..");

    @ArchTest
    static final ArchRule usecases_should_be_in_usecase_package =
        classes().that().haveSimpleNameEndingWith("UseCase")
            .and().resideOutsideOfPackages("..global..", "..common..")
            .should().resideInAPackage("..usecase..");

    @ArchTest
    static final ArchRule application_services_should_be_in_service_package =
        classes().that().haveSimpleNameContaining("Service")
            .and().resideInAPackage("..application..")
            .and().resideOutsideOfPackages("..global..", "..common..")
            .should().resideInAPackage("..service..");

    @ArchTest
    static final ArchRule controllers_should_be_in_controller_package =
        classes().that().haveSimpleNameEndingWith("Controller")
            .and().resideOutsideOfPackages("..global..", "..common..")
            .should().resideInAPackage("..controller..");

    @ArchTest
    static final ArchRule requests_should_be_in_request_package =
        classes().that().haveSimpleNameEndingWith("Request")
            .and().resideOutsideOfPackages("..global..", "..common..")
            .should().resideInAPackage("..request..");

    @ArchTest
    static final ArchRule responses_should_be_in_response_package =
        classes().that().haveSimpleNameEndingWith("Response")
            .and().resideOutsideOfPackages("..global..", "..common..")
            .should().resideInAPackage("..response..");
}
