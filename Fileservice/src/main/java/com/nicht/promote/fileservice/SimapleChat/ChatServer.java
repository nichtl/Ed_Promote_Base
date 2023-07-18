package com.nicht.promote.fileservice.SimapleChat;

import com.nicht.promote.fileservice.SimapleChat.Server.SimpleChatServer;

/**
 * @Description
 * @Date 2023/6/2
 */
public class ChatServer {
    public static void main(String[] args) {
        try {
            int port;
            port = 8082;
            new SimpleChatServer(port).run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
