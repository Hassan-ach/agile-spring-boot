package com.ensa.agile.application.epic.request;

import com.ensa.agile.application.common.request.GetRequest;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class EpicGetRequest extends GetRequest {
    String productId;

    public EpicGetRequest(String productId, String id, String with) {
        super(id, with);
    }
}
