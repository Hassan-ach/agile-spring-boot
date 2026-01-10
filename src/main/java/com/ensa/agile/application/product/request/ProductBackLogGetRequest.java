package com.ensa.agile.application.product.request;

import com.ensa.agile.application.common.request.GetRequest;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ProductBackLogGetRequest extends GetRequest {
    public ProductBackLogGetRequest(String id, String with) { super(id, with); }
}
