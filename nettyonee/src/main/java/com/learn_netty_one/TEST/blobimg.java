package com.learn_netty_one.TEST;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class blobimg {


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //数据库连接
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@192.168.5.134:1521:orcl";
        String user= "SQJZ_FJS";
        String password = "SQJZ_FJS";
        Connection conn = DriverManager.getConnection(url,user, password);

        //读取本地图片
        File file1 = new File("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\time.jpg");
        //得到大小
        int length = (int) file1.length();
        //获得文件输入流
        InputStream input = new FileInputStream(file1);

        //数据库插入图片
        String sql = "INSERT INTO mytest (id,pc) VALUES(?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "1");
        stmt.setBinaryStream(2,input,length);
        stmt.execute();


        //从数据库中读取图片

        //存放数据库中的图片
        File file2 = new File("C:\\Users\\pc\\Desktop\\dec.jpg");
        //从数据库中查找图片
        PreparedStatement stmt1 = conn.prepareStatement("select * from mytest where id = ?");
        stmt1.setObject(1, 1);
        //得到结果
        ResultSet rs = stmt1.executeQuery();
        if (rs.next()) {
            OutputStream fos = new FileOutputStream(file2);
InputStream is = rs.getBinaryStream("pc");
byte[] buffer = new byte[4 * 1024];
int length1 = 0;
while ((length1 = is.read(buffer)) != -1) {
fos.write(buffer, 0, length1);
            }
fos.flush();
fos.close();
is.close();
        }
    }
}