package com.nicht.promote.fileservice.Utils;

import com.nicht.promote.fileservice.Exception.MinioException;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InvalidExpiresRangeException;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/8
 * @Link
 */
@Repository
public class MinioUtils {
    @Autowired
    private  MinioClient  minioClient;
    private static final int MIN_MULTIPART_SIZE = 0;
    private static final int DEFAULT_EXPIRY_TIME = 7 * 24 * 3600;

    @SneakyThrows
    public   boolean bucketIsExists(String  bucketName){
     return    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }
    @SneakyThrows
    public  boolean  createBucket(String  bucketName){
        if(bucketIsExists(bucketName)){
            throw new MinioException("Bucket is already exists");
        }
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        return  true;
    }
    @SneakyThrows
    public List<Bucket> getBucketList(){
        return minioClient.listBuckets();
    }
    @SneakyThrows
    public List<String> getAllBucketName(){
        List<String> Namelist = new ArrayList<>();
        getBucketList().forEach(bucket -> {
            Namelist.add(bucket.name());
        });
        return Namelist;
    }
    @SneakyThrows
    public boolean removeBucket(String bucketName){
        if(bucketIsExists(bucketName)) {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            return true;
        }
        return  false;
    }
    @SneakyThrows
    public  Iterable<Result<Item>> ListObjectInBucket(String  bucketName){
        if(bucketIsExists(bucketName)){
          return  minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return  null;
    }
    @SneakyThrows
    public  boolean putObject(String bucketName, String  objectName,String  fileName){
       if(bucketIsExists(bucketName)){
           if(!ObjectExist(bucketName,objectName)) {
               minioClient.uploadObject(UploadObjectArgs.builder().
                       bucket(bucketName).
                       object(objectName).
                       filename(fileName).
                       build());
               return true;
           }
       }
       return  false;
    }

    /**
     * @description 上传文件
     * @param bucketName
     * @param multipartFile
     * @param fileName
     */
    @SneakyThrows
    public  void putObject(String bucketName , MultipartFile multipartFile ,String fileName){
        minioClient.putObject(PutObjectArgs.builder().
                bucket(bucketName).
                object(fileName).
                contentType(multipartFile.getContentType()).
                stream(multipartFile.getInputStream(),multipartFile.getSize(),MinioUtils.MIN_MULTIPART_SIZE).
                build());
    }


    /**
     * 通过InputStream上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param stream     要上传的流
     * @return
     */
    @SneakyThrows
    public boolean putObject(String bucketName, String objectName, InputStream stream) {
        boolean flag = bucketIsExists(bucketName);
        if (flag) {
            minioClient.putObject(PutObjectArgs.builder().
                    bucket(bucketName).
                    object(objectName).
                    stream(stream,stream.available(),-1).
                    build());
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取对象的元数据
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public ObjectStat statObject(String bucketName, String objectName) {
        boolean flag = bucketIsExists(bucketName);
        if (flag) {
            ObjectStat statObject =  minioClient.statObject(StatObjectArgs.builder().
                    bucket(bucketName).
                    object(objectName).
                    build());
            return statObject;
        }
        return null;
    }
    @SneakyThrows
    public  boolean  ObjectExist(String bucketName,String objectName){
        if(bucketIsExists(bucketName)){
           return  statObject(bucketName,objectName) ==null ? false : true;
        }
        return  false;
    }
    @SneakyThrows
    public  InputStream  getObject(String bucketName, String objectName){
        if(ObjectExist(bucketName,objectName)){
            return  minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
        }
        return  null;
    }
    /**
     * 以流的形式获取一个文件对象（断点下载）
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param offset     起始字节的位置
     * @param length     要读取的长度 (可选，如果无值则代表读到文件结尾)
     * @return
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName, long offset, Long length) {
        if(ObjectExist(bucketName,objectName)){
            return  minioClient.getObject(GetObjectArgs.builder().
                    bucket(bucketName).
                    object(objectName).
                    offset(offset).
                    length(length).
                    build());
        }
        return  null;
    }
    /**
     * 下载并将文件保存到本地
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param fileName   File name
     * @return
     */
    @SneakyThrows
    public boolean getObject(String bucketName, String objectName, String fileName) {
        if(ObjectExist(bucketName,objectName)){
              minioClient.downloadObject(DownloadObjectArgs.builder().
                            bucket(bucketName).
                            object(objectName).
                            filename(fileName).
                             build());
            return true;
        }
        return  false;
    }

    /**
     * 删除指定桶的多个文件对象,返回删除错误的对象列表，全部删除成功，返回空列表
     * @descrption  批量删除
     * @param bucketName  存储桶名称
     * @param objectNames 含有要删除的多个object名称的迭代器对象
     * @return
     */
    @SneakyThrows
    public List<String> removeObject(String bucketName, List<String> objectNames) {
        List<String> errorNames = new ArrayList<>();

        if (bucketIsExists(bucketName)) {
            Iterable<Result<DeleteError>> results =  minioClient.removeObjects(RemoveObjectsArgs.builder().
                    bucket(bucketName).
                    objects(
                      StreamSupport.stream(objectNames.spliterator(), false).map((name) -> {
                      return new DeleteObject(name);
                      })::iterator
                    ).build());
            results.forEach((deleteErrorResult) -> {
                try {
                    errorNames.add(deleteErrorResult.get().objectName());
                }catch (Exception e){e.printStackTrace();}
            });

            return errorNames;
        }
        return  null;
    }



    /**
     * 生成一个给HTTP GET请求用的presigned URL。
     * 浏览器/移动端的客户端可以用这个URL进行下载，即使其所在的存储桶是私有的。这个presigned URL可以设置一个失效时间，默认值是7天。
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天
     * @return
     */
    @SneakyThrows
    public String presignedGetObject(String bucketName, String objectName, Integer expires) {
        boolean flag = bucketIsExists(bucketName);
        String url = "";
        if (flag) {
            if (expires < 1 || expires > DEFAULT_EXPIRY_TIME) {
                throw new InvalidExpiresRangeException(expires,
                        "expires must be in range of 1 to " + DEFAULT_EXPIRY_TIME);
            }
            url = minioClient.presignedGetObject(bucketName, objectName, expires);
        }
        return url;
    }

    /**
     * 生成一个给HTTP PUT请求用的presigned URL。
     * 浏览器/移动端的客户端可以用这个URL进行上传，即使其所在的存储桶是私有的。这个presigned URL可以设置一个失效时间，默认值是7天。
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天
     * @return
     */
    @SneakyThrows
    public String presignedPutObject(String bucketName, String objectName, Integer expires) {
        boolean flag = bucketIsExists(bucketName);
        String url = "";
        if (flag) {
            if (expires < 1 || expires > DEFAULT_EXPIRY_TIME) {
                throw new InvalidExpiresRangeException(expires,
                        "expires must be in range of 1 to " + DEFAULT_EXPIRY_TIME);
            }
            url = minioClient.presignedPutObject(bucketName, objectName, expires);
        }
        return url;
    }


    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        boolean flag = bucketIsExists(bucketName);
        String url = "";
        if (flag) {
            url = minioClient.getObjectUrl(bucketName, objectName);
        }
        return url;
    }


    public void downloadFile(String bucketName, String fileName, String originalName, HttpServletResponse response) {
        try {

            InputStream file = minioClient.getObject(bucketName, fileName);
            String filename = new String(fileName.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
            if (StringUtils.isNotEmpty(originalName)) {
                fileName = originalName;
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = file.read(buffer)) > 0) {
                servletOutputStream.write(buffer, 0, len);
            }
            servletOutputStream.flush();
            file.close();
            servletOutputStream.close();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
