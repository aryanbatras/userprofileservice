package com.sashel.user_profile_service.utility.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    @Value("${minio.bucket}")
    private String bucket;

    @Autowired
    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(String fileName, MultipartFile file, String oldImageUrl) throws IOException {
        if (oldImageUrl != null && !oldImageUrl.isBlank()) {
            String oldKey = extractKeyFromUrl(oldImageUrl);
            deleteFile(oldKey);
        }
        s3Client.putObject(
                PutObjectRequest.builder( )
                        .bucket(bucket)
                        .key(fileName)
                        .contentType(file.getContentType( ))
                        .build( ),
                RequestBody.fromBytes(file.getBytes( ))
        );

        return s3Client.utilities().getUrl(builder ->
                builder.bucket(bucket).key(fileName).region(Region.AP_SOUTH_1)
        ).toExternalForm();
    }

    public void deleteFile(String fileKey) {
        s3Client.deleteObject(builder ->
                builder.bucket(bucket).key(fileKey)
        );
    }

    private String extractKeyFromUrl(String url) {
        int bucketEnd = url.indexOf(".amazonaws.com/") + ".amazonaws.com/".length();
        return url.substring(bucketEnd);
    }

}