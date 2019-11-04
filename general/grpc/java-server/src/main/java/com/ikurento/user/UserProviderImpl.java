package com.ikurento.user;

import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;


public class UserProviderImpl extends UserProviderGrpc.UserProviderImplBase {

    @Override
    public void getUser(UserId request, io.grpc.stub.StreamObserver<User> responseObserver) {
        String id = request.getId();
        User user = User.newBuilder().setId(id)
                .setTime(Timestamp.getDefaultInstance())
                .setAge(11)
                .setName("Hello")
                .setId(id)
                .build();
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserList(UserIdList request, StreamObserver<UserList> responseObserver){
        ProtocolStringList protocolStringList = request.getIdList();
        UserList.Builder userListBuilder = UserList.newBuilder();
        for (String id : protocolStringList) {
            User user = User.newBuilder().setId(id)
                    .setTime(Timestamp.getDefaultInstance())
                    .setAge(11)
                    .setName("Hello")
                    .setId(id)
                    .build();
            userListBuilder.addUser(user);
        }
        responseObserver.onNext(userListBuilder.build());
        responseObserver.onCompleted();
    }

}