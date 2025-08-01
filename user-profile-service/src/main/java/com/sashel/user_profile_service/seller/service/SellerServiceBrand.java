package com.sashel.user_profile_service.seller.service;

import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrand;
import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrandReview;
import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrandStatus;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoBrand;
import com.sashel.user_profile_service.seller.entity.SellerEntityBrand;
import com.sashel.user_profile_service.seller.enums.ReviewStatusEnum;
import com.sashel.user_profile_service.seller.mapper.SellerMapperBrand;
import com.sashel.user_profile_service.seller.repository.SellerRepositoryBrand;
import com.sashel.user_profile_service.utility.service.SnsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceBrand {

    @Autowired
    SellerRepositoryBrand sellerRepositoryBrand;

    @Autowired
    SnsService snsService;

    // TODO: to get brand info
    public SellerResponseDtoBrand getBrandInfo(String sellerId) {
        var sellerBrandEntity = sellerRepositoryBrand.findById(sellerId);
        if(sellerBrandEntity.isPresent()) {
           return SellerMapperBrand.mapSellerEntityBrandToSellerResponseDtoBrand(sellerBrandEntity.get());
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }

//     TODO: to update brand info
    public SellerResponseDtoBrand updateBrandInfo(String sellerId, SellerRequestDtoBrand sellerRequestDtoBrand) {
        var sellerBrandEntity = sellerRepositoryBrand.findById(sellerId);
        if(sellerBrandEntity.isPresent()) {
            var savedSellerEntityBrand = sellerRepositoryBrand.save(
                    SellerMapperBrand.mapSellerRequestDtoBrandToSellerEntityBrand(sellerRequestDtoBrand, sellerBrandEntity.get())
            );
            return SellerMapperBrand.mapSellerEntityBrandToSellerResponseDtoBrand(savedSellerEntityBrand);
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }

    // TODO: to update seller's brand logo
    public String updateBrandLogo(String sellerId, String newImageUrl) {
        var sellerBrandEntity = sellerRepositoryBrand.findById(sellerId);
        if(sellerBrandEntity.isPresent()) {
            sellerBrandEntity.get().setBrandLogo(newImageUrl);
            var savedSellerBrandEntity = sellerRepositoryBrand.save(sellerBrandEntity.get());
            return savedSellerBrandEntity.getBrandLogo();
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }

    // TODO: to create brand
    public void createBrand(String sellerId) {
        SellerEntityBrand sellerBrandEntity = new SellerEntityBrand();
        sellerBrandEntity.setBrandId(sellerId);
        sellerBrandEntity.setReviewStatus(ReviewStatusEnum.INITIAL);
        sellerRepositoryBrand.save(sellerBrandEntity);
    }

    // TODO: to update brand review status
    public Boolean updateBrandStatus(String sellerId, SellerRequestDtoBrandStatus sellerRequestDtoBrandStatus) {
        var sellerBrandEntity = sellerRepositoryBrand.findById(sellerId);
        if(sellerBrandEntity.isPresent()) {
            sellerBrandEntity.get().setReviewStatus(ReviewStatusEnum.valueOf(sellerRequestDtoBrandStatus.getReviewStatus()));
            var savedSellerBrandEntity = sellerRepositoryBrand.save(sellerBrandEntity.get());
            if(savedSellerBrandEntity.getReviewStatus().equals(ReviewStatusEnum.IN_REVIEW_BY_ADMIN)) {
                snsService.notifyAdminForReview(sellerId, savedSellerBrandEntity.getBrandName());
            }
            return true;
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }

    // TODO: to review brand by admin
    public Boolean reviewBrandByAdmin(String sellerId, SellerRequestDtoBrandReview sellerRequestDtoBrandReview) {
        var sellerBrandEntity = sellerRepositoryBrand.findById(sellerId);
        if(sellerBrandEntity.isPresent()) {
            sellerBrandEntity.get().setReviewStatus(ReviewStatusEnum.valueOf(sellerRequestDtoBrandReview.getReviewStatus()));
            sellerBrandEntity.get().setReviewComments(sellerRequestDtoBrandReview.getReviewComments());
            sellerBrandEntity.get().setIsReviewed(sellerRequestDtoBrandReview.getIsReviewed());
            var savedSellerBrandEntity = sellerRepositoryBrand.save(sellerBrandEntity.get());
            if(savedSellerBrandEntity.getReviewStatus().equals(ReviewStatusEnum.ADMIN_REVIEW_APPROVED)) {
                snsService.notifySellerForApproved(sellerId, savedSellerBrandEntity.getBrandName());
            } else if(savedSellerBrandEntity.getReviewStatus().equals(ReviewStatusEnum.ADMIN_REVIEW_REJECTED)) {
                snsService.notifySellerForRejected(sellerId, savedSellerBrandEntity.getBrandName());
            }
            return true;
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }
}
