package com.ensa.agile.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.domain.global.repository.BaseDomainRepository;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.ensa.agile",
                importOptions = DoNotIncludeTests.class)
public class ImplementationContractTest {

    @ArchTest
    static final ArchRule use_cases_should_extend_base_class =
        classes()
            .that()
            .resideInAPackage("..application..usecase..")
            .and()
            .areNotAnonymousClasses()
            .and()
            .areNotInterfaces()
            .should()
            .beAssignableTo(BaseUseCase.class);

    @ArchTest
    static final ArchRule interface_adapter_should_implement_domain_repository =
        classes()
            .that()
            .resideInAnyPackage("..infrastructure.persistence.jpa..")
            .and()
            .haveNameMatching(".*Adapter")
            .should()
            .implement(BaseDomainRepository.class);
}
