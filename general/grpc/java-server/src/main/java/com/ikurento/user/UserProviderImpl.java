package com.ikurento.user;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Timestamp;

public class UserProviderImpl extends UserProviderGrpc.UserProviderImplBase {

    @Override
    public void getUser(UserId request, io.grpc.stub.StreamObserver<User> responseObserver) {
        String id = request.getId();
        User user = User.newBuilder().setId("1")
                .setTime(Timestamp.newBuilder().build())
                .setAge(11)
                .setName("Hello")
                .setId(id)
                .build();
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

}