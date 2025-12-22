package com.ensa.agile.infrastructure.persistence.product.jpa.member;

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

    @Override
    public ProjectMember save(ProjectMember projectMember) {
        return ProjectMemberJpaMapper.toDomainEntity(
            jpaProjectMemberRepository.save(
                ProjectMemberJpaMapper.toJpaEntity(projectMember)));
    }

    @Override
    public ProjectMember findById(String id) {
        return ProjectMemberJpaMapper.toDomainEntity(
            jpaProjectMemberRepository.findById(id).orElseThrow(
                ProjectMemberNotFoundException::new));
    }

    @Override
    public List<ProjectMember> findAll() {
        return jpaProjectMemberRepository.findAll()
            .stream()
            .map(ProjectMemberJpaMapper::toDomainEntity)
            .toList();
    }

    @Override
    public void deleteById(String projectMemberId) {
        jpaProjectMemberRepository.deleteById(projectMemberId);
    }

    @Override
    public boolean existsById(String id) {
        return jpaProjectMemberRepository.existsById(id);
    }

    @Override
    public boolean existsByProductBackLogIdAndUserId(String productBackLogId,
                                                     String userId) {
        return jpaProjectMemberRepository.existsByUser_IdAndProductBackLog_Id(
            userId, productBackLogId);
    }

    @Override
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

    @Override
    public void deleteByUserEmailAndProductBackLogId(String userEmail,
                                                     String productBackLogId) {

        jpaProjectMemberRepository.deleteByUser_EmailAndProductBackLog_Id(
            userEmail, productBackLogId);
    }

    @Override
    public boolean existsByUserEmailAndProductBackLogIdAndRole(
        String userEmail, String productBackLogId, RoleType role) {

        return jpaProjectMemberRepository
            .existsByUser_EmailAndProductBackLog_IdAndRole(
                userEmail, productBackLogId, role);
    }

    @Override
    public ProjectMember
    findByUserIdAndProductBackLogId(String userId, String productBackLogId) {
        return ProjectMemberJpaMapper.toDomainEntity(
            jpaProjectMemberRepository
                .findByUser_IdAndProductBackLog_Id(userId, productBackLogId)
                .orElseThrow(ProjectMemberNotFoundException::new));
    }
}
