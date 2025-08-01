package com.sashel.user_profile_service.utility.service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.regions.Region;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;


@Service
@RequiredArgsConstructor
public class SnsService {

    private final String topicArn = "arn:aws:sns:ap-south-1:123456789012:USER_PROFILE_TOPIC";
    private final Region region = Region.AP_SOUTH_1;

    public void notifyAdminForReview(String sellerId, String brandName) {
        try (SnsClient snsClient = SnsClient.create()) {

            String message = "Seller " + brandName + " (ID: " + sellerId + ") has completed KYC. Please review their profile.";
            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(message)
                    .subject("KYC Review Needed")
                    .build();

            PublishResponse response = snsClient.publish(request);
        } catch (SnsException e) {
            System.err.println(e.getMessage());
        }
    }

    public void notifySellerForApproved(String sellerId, String brandName) {
        try (SnsClient snsClient = SnsClient.create()) {

            String message = "Your KYC has been approved. You can now start selling on our platform.";
            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(message + " Seller ID: " + sellerId + " Brand Name: " + brandName)
                    .subject("KYC Approved")
                    .build();

            PublishResponse response = snsClient.publish(request);
        } catch (SnsException e) {
            System.err.println(e.getMessage());
        }
    }
    public void notifySellerForRejected(String sellerId, String brandName) {
        try (SnsClient snsClient = SnsClient.create()) {

            String message = "Your KYC has been rejected. Please update your KYC and try again.";
            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(message + " Seller ID: " + sellerId + " Brand Name: " + brandName)
                    .subject("KYC Rejected")
                    .build();

            PublishResponse response = snsClient.publish(request);
        } catch (SnsException e) {
            System.err.println(e.getMessage());
        }
    }
}