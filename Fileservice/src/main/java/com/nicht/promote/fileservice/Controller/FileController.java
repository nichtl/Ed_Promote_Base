package com.nicht.promote.fileservice.Controller;

import com.nicht.promote.fileservice.Utils.MinioUtils;
import com.nicht.promote.fileservice.config.MinioProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author ni
 * @description
 * @ 2021/2/26
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private MinioUtils minioUtils;

    @PostMapping(value = "/uploadFile")
    public String uploadFile(@RequestBody MultipartFile multipartFile) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String newFilename = UUID.randomUUID().toString();
        String suffix = multipartFile.getOriginalFilename().split("\\.")[1];
        minioUtils.putObject(minioProperties.getBucketName(), multipartFile, date + "/" + newFilename + "." + suffix);
        String url = "http://" + minioProperties.getEndpoint() + ":" + minioProperties.getPort() + "/" +
                minioProperties.getBucketName() + "/" + date + "/" + newFilename +
                "." + suffix;
        return url;
    }


    @GetMapping(value = "/getfile")
    public  void getFile( @RequestParam(value = "bucketname") String bucketname, @RequestParam(value = "filename") String  filename){
        minioUtils.downloadFile(bucketname,filename,filename,((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse());
    }











}
