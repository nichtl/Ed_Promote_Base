package com.learn_netty_one.Nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author ni
 * @description
 * @Link https://ifeve.com/buffers/
 * @ 2021/3/15
 */
public class NioBuffer {
        /*  BUFFER的数据是会被覆盖的
            Java NIO Buffers 通常在NIO通道交互中使用 用于缓存通道数据 或者 让通道读数据
            Java NIO Buffers本质上就是一个内存块可以进行读写操作，这个内存块被包装在NIO Buffer对象里(八大基本数据类型数组)并提供一系列方法用于操作这个内存块
            使用buffer 读写数据一般分为四个步骤 需要注意一般通道
            1向空buf中写入数据    每次读写都会对buffer中的position+1标识未读位置
            2读之前使用 buf.filp() 使position为0 从缓冲区头开始读 写之前使用buf.clear() 或 buf.compact()清理缓存区为写入做准备
            3读数据
            4buf.clear() clear方法不会删除已读的数据 他将position的位置重新设置为0，limit设置成 capacity的值,，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有，即将原来的数据选择遗忘 这样在下次重新写入数据的时候原位置的数据会被新数据覆盖
            buf.compact() 清除已读取的数据并将未读数据复制到缓冲区头部 并将position修正到未读数据的length+1
            mark() buffer中的mark主要用于标记buffer的特定位置 之后配合reset() 方法可以恢复将position恢复到这个位置 主要用于特定状态的判断和回退
            Buffer可分为以下几大类
            Java NIO 有以下Buffer类型
            ByteBuffer 字节buffer
            MappedByteBuffer 映射字节
            CharBuffer      char
            DoubleBuffer
            FloatBuffer
            IntBuffer
            LongBuffer
            ShortBuffer 这些Buffer类型代表了不同的数据类型。换句话说，就是可以通过char，short，int，long，float 或 double类型来操作缓冲区中的字节。
            */
        /*荔枝*/
        public static void main(String[] args) {
            try {
                String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "readme.txt";
                System.out.println(path);
                RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
                FileChannel fileChannel = randomAccessFile.getChannel(); //要向缓冲区放入什么数据使用对应buf
                ByteBuffer byteBuffer =  ByteBuffer.allocate(18);
                Integer  bufposition = fileChannel.read(byteBuffer); //read() 方法返回有多少字符被写入缓冲区 如果返回 -1 代表已经到了文件末尾
                for(;;){
                    if(bufposition==-1){
                        break;
                    }
                    byteBuffer.flip();
                    for(;;){
                        if(!byteBuffer.hasRemaining()){break;}
                        System.out.println((char) byteBuffer.get());
                    }
                    byteBuffer.clear();
                    bufposition = fileChannel.read(byteBuffer);
                }
                randomAccessFile.close();//
            }catch (Exception e){
                e.printStackTrace();
            }
        }
















}
