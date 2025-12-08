
package com.ensa.agile.application.product.usecase;

import com.ensa.agile.application.global.BaseUseCase;
import com.ensa.agile.application.global.ITransactionCallBack;
import com.ensa.agile.application.global.ITransactionalWrapper;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.request.ProductBackLogUpdateRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateProductBackLogInfoUseCase
    implements BaseUseCase<ProductBackLogUpdateRequest,
                           ProductBackLogResponse> {
  private final ProductBackLogRepository productBackLogRepository;
  private final ITransactionalWrapper transactionalWrapper;

  public ProductBackLogResponse execute(ProductBackLogUpdateRequest data) {
    return transactionalWrapper.execute(
        new ITransactionCallBack<ProductBackLogResponse>() {
          @Override
          public ProductBackLogResponse execute() {
            ProductBackLog productBackLog =
                productBackLogRepository.findById(data.getId());

            return ProductBackLogResponseMapper.tResponse(
                productBackLogRepository.save(merge(productBackLog, data)));
          }
        });
  }

  private ProductBackLog merge(ProductBackLog oldProductBackLog,
                               ProductBackLogUpdateRequest newProductBackLog) {
    if (newProductBackLog.getName() != null) {
      oldProductBackLog.setName(newProductBackLog.getName());
    }
    if (newProductBackLog.getDescription() != null) {
      oldProductBackLog.setDescription(newProductBackLog.getDescription());
    }

    return oldProductBackLog;
  }
}
