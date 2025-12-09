package com.ensa.agile.application.common.useCase;

import com.ensa.agile.application.common.request.InviteRequest;
import com.ensa.agile.application.common.response.InviteResponse;
import com.ensa.agile.application.global.service.ICurrentUser;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.product.exception.UserAlreadyInvitedException;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.entity.ProjectMember;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import com.ensa.agile.domain.product.repository.ProjectMemberRepository;
import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.user.repository.UserRepository;

public abstract class InviteUseCase
    extends BaseUseCase<InviteRequest, InviteResponse> {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProductBackLogRepository productBackLogRepository;
    private final UserRepository userRepository;
    private final ICurrentUser currentUserService;

    public InviteUseCase(ProjectMemberRepository projectMemberRepository,
                         ProductBackLogRepository productBackLogRepository,
                         UserRepository userRepository,
                         ICurrentUser currentUserService,
                         ITransactionalWrapper transactionalWrapper) {

        super(transactionalWrapper);
        this.projectMemberRepository = projectMemberRepository;
        this.productBackLogRepository = productBackLogRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    abstract public InviteResponse execute(InviteRequest data);

    public InviteResponse executeInvitaion(InviteRequest data) {
        return invite(data, RoleType.MEMBER);
    }

    public InviteResponse executeInvitaion(InviteRequest data,
                                           RoleType roleType) {
        return invite(data, roleType);
    }

    private InviteResponse invite(InviteRequest data, RoleType roleType) {
        User currentUser = currentUserService.getCurrentUser();
        if (currentUser.getEmail() == data.getEmail()) {
            throw new IllegalStateException("You cannot invite yourself as " +
                                            roleType);
        }

        if (isUserAlreadyInvited(data.getEmail(), data.getProductId())) {
            throw new UserAlreadyInvitedException();
        }

        User u = userRepository.findByEmail(data.getEmail());
        ProductBackLog pb =
            productBackLogRepository.findById(data.getProductId());

        ProjectMember invitation = ProjectMember.builder()
                                       .user(u)
                                       .productBackLog(pb)
                                       .role(roleType)
                                       .build();
        ProjectMember invitedSaved = projectMemberRepository.save(invitation);
        return InviteResponse.builder()
            .message("User " + u.getEmail() + " invited successfully as " +
                     roleType)
            .success(true)
            .inviteId(invitedSaved.getId())
            .build();
    }

    private boolean isUserAlreadyInvited(String email, String productId) {
        return projectMemberRepository.existsByUserEmailAndProductBackLogId(
            email, productId);
    }
}
