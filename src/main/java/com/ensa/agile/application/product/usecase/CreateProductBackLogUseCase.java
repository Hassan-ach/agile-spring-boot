package com.ensa.agile.application.product.usecase;

import com.ensa.agile.application.global.BaseUseCase;
import com.ensa.agile.application.global.ICurrentUser;
import com.ensa.agile.application.global.ITransactionCallBack;
import com.ensa.agile.application.global.ITransactionalWrapper;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.request.ProductBackLogCreateRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.entity.ProjectMember;
import com.ensa.agile.domain.product.enums.MemberStatus;
import com.ensa.agile.domain.product.enums.RoleType;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import com.ensa.agile.domain.product.repository.ProjectMemberRepository;
import com.ensa.agile.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateProductBackLogUseCase
    implements BaseUseCase<ProductBackLogCreateRequest,
                           ProductBackLogResponse> {

  private final ProductBackLogRepository productBackLogRepository;
  private final ProjectMemberRepository projectMemberRepository;
  private final ICurrentUser currentUserService;
  private final ITransactionalWrapper transactionalWrapper;
  public ProductBackLogResponse execute(ProductBackLogCreateRequest request) {
    return transactionalWrapper.execute(
        new ITransactionCallBack<ProductBackLogResponse>() {
          @Override
          public ProductBackLogResponse execute() {
            ProductBackLog pb = ProductBackLog.builder()
                                    .name(request.getName())
                                    .description(request.getDescription())
                                    .build();
            ProductBackLog pbSaved = productBackLogRepository.save(pb);

            User u = currentUserService.getCurrentUser();
            ProjectMember pm = ProjectMember.builder()
                                   .user(u)
                                   .productBackLog(pbSaved)
                                   .role(RoleType.PRODUCT_OWNER)
                                   .status(MemberStatus.ACTIVE)
                                   .build();

            projectMemberRepository.save(pm);

            ProductBackLogResponse response =
                ProductBackLogResponseMapper.tResponse(pbSaved);
            return response;
          }
        });
  }

}
