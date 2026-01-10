package com.ensa.agile.application.story.request;

import com.ensa.agile.application.common.request.GetRequest;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class UserStoryGetRequest extends GetRequest {

    public UserStoryGetRequest(String id, String with) { super(id, with); }
}
