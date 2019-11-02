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
    }

    private void testGetUser() throws Exception {
        try {
            EchoService echoService = (EchoService)userProvider;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UserId userId =  UserId.newBuilder().setId("A003").build();
            User user1 = userProvider.getUser(userId);
            System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " +
                    " UserInfo, Id:" + user1.getId() + ", name:" + user1.getName() + ", sex:" + user1.getSex().toString()
                    + ", age:" + user1.getAge() + ", time:" + user1.getTime().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
