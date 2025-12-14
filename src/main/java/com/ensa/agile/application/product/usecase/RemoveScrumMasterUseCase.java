package com.ensa.agile.application.product.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.common.request.RemoveRequest;
import com.ensa.agile.application.common.response.RemoveResponse;
import com.ensa.agile.application.common.useCase.RemoveUseCase;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.domain.product.repository.ProjectMemberRepository;
import com.ensa.agile.domain.user.repository.UserRepository;

@Component
public class RemoveScrumMasterUseCase extends RemoveUseCase {
    public RemoveScrumMasterUseCase(ITransactionalWrapper tr,
                                    ProjectMemberRepository pm,
                                    UserRepository ur) {
        super(tr, pm, ur);
    }

    @Override
    public RemoveResponse execute(RemoveRequest request) {
        return super.removeUserWithRole(request, RoleType.SCRUM_MASTER);
    }
}
