package com.nicht.promote.fileservice.zookee;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Date 2022/11/2
 */
public class zkClient {
    private static final String ADDRES = "82.156.165.91:2181";
    private static final int TIMAOUT = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {

        /**
         * 参数一：连接地址
         * 参数二：zk超时时间
         * 参数三：事件通知
         */
        //1、创建zk链接

        ZkClient zkClient = new ZkClient(ADDRES, TIMAOUT);
//        ZooKeeper zooKeeper = new ZooKeeper(ADDRES, TIMAOUT, new Watcher() {
//            @Override
//            public void process(WatchedEvent watchedEvent) {
//                Event.KeeperState state = watchedEvent.getState();
//                if(state == Event.KeeperState.SyncConnected){
//                    System.out.println("zk链接成功");
//                    countDownLatch.countDown(); //计数器减 1
//                }
//                else {
//                    System.out.println("zk connect error ="+watchedEvent.getState().toString());
//                }
//            }
//        });

        //计数器结果必须是为0 才能继续执行
        System.out.println("zk正在等待连接");
         //countDownLatch.await();
        System.out.println("开始创建我们的连接");
        //2、创建我们的节点
        /**
         * 参数一：路径名称
         * 参数二：节点value
         * 参数三：节点权限acl
         * 蚕食四：节点类型 临时和永久
         */
//        System.out.println(zooKeeper.getState().isConnected());
//        String s = zooKeeper.create("/test/two", "hello zk two".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        //String bs = new String(zooKeeper.getData("/test/one",false,new Stat()));
//         List<String> nodes =  zooKeeper.getChildren("/test", new Watcher() {
//             @Override
//             public void process(WatchedEvent watchedEvent) {
//                 System.out.println(watchedEvent.getType().toString());
//                 System.out.println(watchedEvent.toString());
//                 System.out.println(watchedEvent.getState().toString());;
//             }
//         }, new Stat());
    List<String> os = zkClient.getChildren("/test");
    System.out.println(1 );

    }
}
