package com.ensa.agile.application.story.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.story.request.UserStoryGetRequest;
import com.ensa.agile.application.story.response.UserStoryResponse;

public class GetUserStoryUseCase
    extends BaseUseCase<UserStoryGetRequest, UserStoryResponse> {

    public GetUserStoryUseCase(ITransactionalWrapper tr) {
        super(tr);
        //
    }

    @Override
    public UserStoryResponse execute(UserStoryGetRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
}
