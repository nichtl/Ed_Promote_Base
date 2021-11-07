package com.nicht.promote.Classload;

import java.io.*;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/9/22
 * @Link
 */
public class arreytobytes {
    public static void main(String[] args) throws Exception {
        Test360 test =new Test360();
        System.out.print ( "java class对象转字节数组\n" );
        byte[] bufobject = getBytesFromObject(test);
        for(int i=0 ; i<bufobject.length ; i++) {
            System.out.print(bufobject[i] + ",");
        }
        System.out.println ("\n");
        System.out.print ("字节数组还原对象\n");
        Object object1 = null;
        object1=deserialize(bufobject);
        Test360 t1 =(Test360)object1;
        System.out.println (t1.name);
    }
    public static byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bo);
        oos.writeObject(obj);
        return bo.toByteArray();
    }
    public static Object deserialize(byte[] bytes) {
        Object object = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);//
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return object;
    }

}
