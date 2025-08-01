package com.sashel.user_profile_service.buyer.service;

import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDto;
import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDtoStatus;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDto;
import com.sashel.user_profile_service.buyer.entity.BuyerEntity;
import com.sashel.user_profile_service.buyer.mapper.BuyerMapper;
import com.sashel.user_profile_service.buyer.repository.BuyerRepository;
import com.sashel.user_profile_service.buyer.specification.BuyerSpecification;
import com.sashel.user_profile_service.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
public class BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    // TODO: Return BuyerEntity object by Id
    public BuyerResponseDto getBuyer(String buyerId) {
        return buyerRepository.findById(buyerId)
                .map(BuyerMapper::mapBuyerEntityToBuyerResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Buyer not found"));
    }

    // TODO: Return paginated and filtered list of BuyerEntity objects
    public Page<BuyerEntity> getAllBuyersWithFilters(String gender, Boolean isActive, Integer minAge, Integer maxAge, Pageable pageable) {

        Specification<BuyerEntity> spec = (root, query, criteriaBuilder) -> null;

        spec = (gender == null)
                ? spec
                : spec.and(BuyerSpecification.hasGender(gender));

        spec = (isActive == null)
                ? spec
                : spec.and(BuyerSpecification.isActive(isActive));

        spec = ((minAge == null) && (maxAge == null))
                ? spec
                : spec.and(BuyerSpecification.ageBetween(minAge, maxAge));

        return buyerRepository.findAll(spec, pageable);
    }

    // TODO: to create buyer and set phone number
    public void createBuyer(UserEntity userEntity, String userPhone) {
        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setUserEntity(userEntity);
        buyerEntity.setPhone(userPhone);
        buyerRepository.save(buyerEntity);
    }

    // TODO: to update buyer information
    public BuyerResponseDto updateBuyer(String buyerId, BuyerRequestDto buyerRequestDto) {
        var buyerEntity = buyerRepository.findById(buyerId);
        if(buyerEntity.isPresent()) {
            buyerEntity.get().setName(buyerRequestDto.getName());
            buyerEntity.get().setDob(buyerRequestDto.getDob());
            buyerEntity.get().setGender(buyerRequestDto.getGender());
            var savedBuyerEntity = buyerRepository.save(buyerEntity.get());
            return BuyerMapper.mapBuyerEntityToBuyerResponseDto(savedBuyerEntity);
        } else {
            throw new EntityNotFoundException("Buyer not found");
        }
    }

    // TODO: to update buyer status
    public BuyerResponseDto updateBuyerStatus(String sellerId, BuyerRequestDtoStatus buyerRequestDtoStatus) {
        var buyerEntity = buyerRepository.findById(sellerId);
        if (buyerEntity.isPresent( )) {
            buyerEntity.get( ).setIsActive(buyerRequestDtoStatus.getIsActive( ));
            var savedBuyerEntity = buyerRepository.save(buyerEntity.get( ));
            return BuyerMapper.mapBuyerEntityToBuyerResponseDto(savedBuyerEntity);
        } else {
            throw new EntityNotFoundException("Buyer not found");
        }
    }
}
