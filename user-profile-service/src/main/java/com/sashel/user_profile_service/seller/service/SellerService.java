package com.sashel.user_profile_service.seller.service;

import com.sashel.user_profile_service.buyer.entity.BuyerEntity;
import com.sashel.user_profile_service.buyer.specification.BuyerSpecification;
import com.sashel.user_profile_service.seller.dto.request.SellerRequestDto;
import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoStatus;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDto;
import com.sashel.user_profile_service.seller.mapper.SellerMapper;
import com.sashel.user_profile_service.seller.repository.SellerRepository;
import com.sashel.user_profile_service.seller.entity.SellerEntity;
import com.sashel.user_profile_service.seller.specification.SellerSpecification;
import com.sashel.user_profile_service.user.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    // TODO: to create seller and set email
    public void createSeller(UserEntity userEntity, String userEmail) {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setUserEntity(userEntity);
        sellerEntity.setEmail(userEmail);
        sellerRepository.save(sellerEntity);
    }

    // TODO: to get paginated and filtered list of sellers
    public Page<SellerEntity> getAllSellersWithFilters(String gender, Boolean isActive, Integer minAge, Integer maxAge, Pageable pageable) {
        Specification<SellerEntity> spec = (root, query, criteriaBuilder) -> null;

        spec = (gender == null)
                ? spec
                : spec.and(SellerSpecification.hasGender(gender));

        spec = (isActive == null)
                ? spec
                : spec.and(SellerSpecification.isActive(isActive));

        spec = ((minAge == null) && (maxAge == null))
                ? spec
                : spec.and(SellerSpecification.ageBetween(minAge, maxAge));

        return sellerRepository.findAll(spec, pageable);
    }

    // TODO: to get seller by Id
    public SellerResponseDto getSeller(String sellerId) {
        return sellerRepository.findById(sellerId)
                .map(SellerMapper::mapSellerEntityToSellerResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }

    // TODO: to update seller
    public SellerResponseDto updateSeller(String sellerId, SellerRequestDto sellerRequestDto) {
        var sellerEntity = sellerRepository.findById(sellerId);
        if(sellerEntity.isPresent()) {
            sellerEntity.get().setName(sellerRequestDto.getName());
            sellerEntity.get().setDob(Date.valueOf(sellerRequestDto.getDob()));
            sellerEntity.get().setGender(sellerRequestDto.getGender());
            sellerEntity.get().setPhone(sellerRequestDto.getPhone());
            sellerEntity.get().setPincode(sellerRequestDto.getPincode());
            sellerEntity.get().setCity(sellerRequestDto.getCity());
            sellerEntity.get().setState(sellerRequestDto.getState());
            sellerEntity.get().setCountry(sellerRequestDto.getCountry());
            sellerEntity.get().setLandmark(sellerRequestDto.getLandmark());
            var savedSellerEntity = sellerRepository.save(sellerEntity.get());
            return SellerMapper.mapSellerEntityToSellerResponseDto(savedSellerEntity);
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }

    // TODO: to update seller status
    public SellerResponseDto updateSellerStatus(String sellerId, SellerRequestDtoStatus sellerRequestDtoStatus) {
        var sellerEntity = sellerRepository.findById(sellerId);
        if(sellerEntity.isPresent()) {
            sellerEntity.get().setIsActive(sellerRequestDtoStatus.getIsActive());
            var savedSellerEntity = sellerRepository.save(sellerEntity.get());
            return SellerMapper.mapSellerEntityToSellerResponseDto(savedSellerEntity);
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }

    // TODO: to update seller's profile image
    public String updateProfileImage(String userId, String newImageUrl) {
        var sellerEntity = sellerRepository.findById(userId);
        if(sellerEntity.isPresent()) {
            sellerEntity.get().setProfileImage(newImageUrl);
            var savedSellerEntity = sellerRepository.save(sellerEntity.get());
            return savedSellerEntity.getProfileImage();
        } else {
            throw new EntityNotFoundException("Seller not found");
        }
    }
}

