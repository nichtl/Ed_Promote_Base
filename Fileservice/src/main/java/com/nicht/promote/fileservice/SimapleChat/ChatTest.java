package com.nicht.promote.fileservice.SimapleChat;


import com.nicht.promote.fileservice.SimapleChat.Client.SimpleChatClient;

/**
 * @Author ni
 * @description
 * @ 2021/1/5
 */
public class ChatTest {

    public static void main(String[] args) throws  Exception {
        new SimpleChatClient("127.0.0.1", 8082).run();
    }
}
