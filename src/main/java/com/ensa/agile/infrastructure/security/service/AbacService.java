package com.ensa.agile.infrastructure.security.service;

import com.ensa.agile.application.global.service.IAbacService;
import com.ensa.agile.application.global.service.ICurrentUser;
import com.ensa.agile.domain.product.entity.ProjectMember;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.domain.product.repository.ProjectMemberRepository;
import com.ensa.agile.domain.sprint.enums.SprintStatus;
import com.ensa.agile.domain.sprint.repository.SprintBackLongRepository;
import com.ensa.agile.domain.sprint.repository.SprintMembersRepository;
import com.ensa.agile.domain.user.entity.User;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("abacService")
public class AbacService implements IAbacService {

    private final ICurrentUser currentUserService;
    private final ProjectMemberRepository projectMemberRepository;
    private final SprintMembersRepository sprintMembersRepository;
    private final SprintBackLongRepository sprintBackLongRepository;

    public boolean canAccessProject(String projectId, String action) {

        return switch (action) {
            case "UPDATE", "DELETE" ->
                hasProjectRole(projectId, RoleType.PRODUCT_OWNER);
            case "VIEW" ->
                hasProjectRole(projectId, RoleType.DEVELOPER,
                               RoleType.SCRUM_MASTER, RoleType.PRODUCT_OWNER,
                               RoleType.MEMBER);

            default -> false;
        };
    }

    public boolean canAccessSprint(String sprintId, String action) {

        return false;
    }

    private boolean isSprintHasStatus(String id, SprintStatus status) {
        return sprintBackLongRepository.existsByStatus(status);
    }

    private boolean isSprintMember(String sprintId) {
        User currentUser = currentUserService.getCurrentUser();
        return sprintMembersRepository.existsBySprintBackLogIdAndUserId(
            sprintId, currentUser.getId());
    }

    private boolean hasProjectRole(String projectId, RoleType... roles) {
        User currentUser = currentUserService.getCurrentUser();
        ProjectMember pm =
            this.projectMemberRepository.findByUserIdAndProductBackLogId(
                currentUser.getId(), projectId);
        return pm != null && Arrays.asList(roles).contains(pm.getRole());
    }
}
