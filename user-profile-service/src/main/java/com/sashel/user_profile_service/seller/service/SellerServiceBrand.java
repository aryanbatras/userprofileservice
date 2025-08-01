package com.sashel.user_profile_service.seller.service;

import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrand;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoBrand;
import com.sashel.user_profile_service.seller.entity.SellerEntityBrand;
import com.sashel.user_profile_service.seller.repository.SellerRepositoryBrand;
import com.sashel.user_profile_service.seller.mapper.SellerMapperBrand;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceBrand {

    @Autowired
    SellerRepositoryBrand sellerRepositoryBrand;

    // TODO: to get brand info
    public SellerResponseDtoBrand getBrandInfo(String sellerId) {
        var sellerBrandEntity = sellerRepositoryBrand.findById(sellerId);
        if(sellerBrandEntity.isPresent()) {
           return SellerMapperBrand.mapSellerEntityBrandToSellerResponseDtoBrand(sellerBrandEntity.get());
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }

    // TODO: to create brand info
    public SellerResponseDtoBrand createBrandInfo(String sellerId, SellerRequestDtoBrand sellerRequestDtoBrand) {
        SellerEntityBrand sellerEntityBrand = new SellerEntityBrand();
        sellerEntityBrand.setBrandId(sellerId);
        var savedSellerEntityBrand = sellerRepositoryBrand.save(
                SellerMapperBrand.mapSellerRequestDtoBrandToSellerEntityBrand(sellerRequestDtoBrand, sellerEntityBrand)
        );
        return SellerMapperBrand.mapSellerEntityBrandToSellerResponseDtoBrand(savedSellerEntityBrand);
    }

    // TODO: to update brand info
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
}
