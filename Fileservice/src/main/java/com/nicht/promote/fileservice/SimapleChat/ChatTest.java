package com.nicht.promote.fileservice.SimapleChat;


import com.nicht.promote.fileservice.SimapleChat.Client.SimpleChatClient;

import java.io.File;
import java.io.FileWriter;

/**
 * @Author ni
 * @description
 * @ 2021/1/5
 */
public class ChatTest {





    public static void main(String[] args) throws  Exception {
       File file = new File("queueData.txt");

        System.out.println(
       file.getAbsolutePath()
        );
    }
}
