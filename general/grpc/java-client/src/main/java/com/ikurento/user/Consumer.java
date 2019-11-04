/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ikurento.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.alibaba.dubbo.rpc.service.EchoService;

import java.util.List;

public class Consumer {
    // Define a private variable (Required in Spring)
    private UserProviderGrpc.IUserProvider userProvider;

    // Spring DI (Required in Spring)
    public void setUserProvider(UserProviderGrpc.IUserProvider u) {
        this.userProvider = u;
    }

    // Start the entry function for consumer (Specified in the configuration file)
    public void start() throws Exception {
        System.out.println("\n\ntest");
        testGetUser();
        System.out.println("\n\ntestList");
        testGetUserList();
        System.out.println("\n\ntestErr");
        testGetErr();
    }

    private void testGetUser() throws Exception {
        try {
            UserId userId = UserId.newBuilder().setId("A003").build();
            User user1 = userProvider.getUser(userId);
            System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " +
                    " UserInfo, Id:" + user1.getId() + ", name:" + user1.getName() + ", sex:" + user1.getSex().toString()
                    + ", age:" + user1.getAge() + ", time:" + user1.getTime().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testGetUserList() throws Exception {
        try {
            UserIdList userIdList = UserIdList.newBuilder().addId("A003").addId("A002").build();
            UserList userList = userProvider.getUserList(userIdList);
            for (User user : userList.getUserList()) {
                System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " +
                        " UserInfo, Id:" + user.getId() + ", name:" + user.getName() + ", sex:" + user.getSex().toString()
                        + ", age:" + user.getAge() + ", time:" + user.getTime().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testGetErr() throws Exception {
        try {
            UserId userId = UserId.newBuilder().setId("A003").build();
            userProvider.getErr(userId);
        } catch (Throwable t) {
            System.out.println("*************exception***********");
            t.printStackTrace();
        }
    }
}
