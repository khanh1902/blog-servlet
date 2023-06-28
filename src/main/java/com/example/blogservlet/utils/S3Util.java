package com.example.blogservlet.utils;

import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;

public class S3Util {

    private static final String BUCKET = "upload-image-shoppingcart";
    private static final String ACCESSKEY = "AKIA4YKHWLEWTTHASWHS";
    private static final String SECRETKEY = "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";

    public static void uploadFile(String fileName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {

        AwsCredentialsProvider credentialsProvider = AwsCredentialsProviderChain.builder()
                .credentialsProviders(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESSKEY,
                                SECRETKEY)),
                        DefaultCredentialsProvider.create()
                )
                .build();
        S3Client client = S3Client.builder()
                .region(Region.of("ap-southeast-1"))
                .credentialsProvider(credentialsProvider)
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .acl("public-read")
                .build();
        client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}
