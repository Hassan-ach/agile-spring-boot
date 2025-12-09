package com.ensa.agile.infrastructure.persistence.product.jpa.projectMember;

import com.ensa.agile.application.product.exception.ProjectMemberNotFoundException;
import com.ensa.agile.domain.product.entity.ProjectMember;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.domain.product.repository.ProjectMemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectMemberRepositoryAdapter implements ProjectMemberRepository {
    private final JpaProjectMemberRepository jpaProjectMemberRepository;

    public ProjectMember save(ProjectMember projectMember) {
        return ProjectMemberJpaMapper.toDomainEntity(
            jpaProjectMemberRepository.save(
                ProjectMemberJpaMapper.toJpaEntity(projectMember)));
    }

    public ProjectMember findById(String id) {
        return ProjectMemberJpaMapper.toDomainEntity(
            jpaProjectMemberRepository.findById(id).orElseThrow(
                () -> new ProjectMemberNotFoundException()));
    }

    public List<ProjectMember> findAll() {
        return jpaProjectMemberRepository.findAll()
            .stream()
            .map(ProjectMemberJpaMapper::toDomainEntity)
            .toList();
    }

    public void deleteById(String projectMemberId) {
        jpaProjectMemberRepository.deleteById(projectMemberId);
    }

    public boolean existsById(String id) {
        return jpaProjectMemberRepository.existsById(id);
    }

    public boolean existsByProductBackLogIdAndUserId(String productBackLogId,
                                                     String userId) {
        return jpaProjectMemberRepository.existsByUser_IdAndProductBackLog_Id(
            userId, productBackLogId);
    }

    public boolean
    existsByUserEmailAndProductBackLogId(String userEmail,
                                         String productBackLogId) {
        return jpaProjectMemberRepository
            .existsByUser_EmailAndProductBackLog_Id(userEmail,
                                                    productBackLogId);
    }

    @Override
    public boolean existsByUserIdAndProductBackLogIdAndRole(
        String userId, String productBackLogId, RoleType role) {
        return jpaProjectMemberRepository
            .existsByUser_IdAndProductBackLog_IdAndRole(userId,
                                                        productBackLogId, role);
    }
}
