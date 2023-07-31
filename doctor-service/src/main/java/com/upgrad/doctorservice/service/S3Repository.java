package com.upgrad.doctorservice.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class S3Repository {

    private AmazonS3 s3Client;
    private String BUCKET_NAME = "upgrad-bmc-repo";// This needs to be a unique bucket name across all the regions.
    //ObjectMetadata metadata;

    @Autowired
    public S3Repository( ){
        //this.metadata = metadata;
        String accessKey = "AKIA4JO4AH56CD76Y2XC";
        String secretKey = "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";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public void uploadFiles(String userId, MultipartFile file) throws IOException {
        String key = userId + "/"+ file.getOriginalFilename();
        if(!s3Client.doesBucketExistV2(BUCKET_NAME)){
            s3Client.createBucket(BUCKET_NAME);
        }
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3Client.putObject(BUCKET_NAME,key,file.getInputStream(), metadata);
    }

    public String getUploadedDocuments(String doctorId){
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(BUCKET_NAME);
        ObjectListing objectListing = s3Client.listObjects(listObjectsRequest);

        List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

        StringBuilder sb = new StringBuilder("");
        for(S3ObjectSummary s3ObjectSummary: objectSummaries) {
            if(s3ObjectSummary.getKey().contains(doctorId)){
                sb.append(s3ObjectSummary.getKey().substring(doctorId.length() + 1));
                sb.append("\n");
            }
        }

        return sb.toString();

    }



}
