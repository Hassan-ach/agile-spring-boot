package com.ensa.agile.application.global.service;

import com.ensa.agile.application.product.request.ProductBackLogGetRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;

public interface IFetchService {
    ProductBackLogResponse getResponse(ProductBackLogGetRequest req);
}
