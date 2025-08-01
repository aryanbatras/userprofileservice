package com.sashel.user_profile_service.buyer.service;

import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDtoAddress;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDtoAddress;
import com.sashel.user_profile_service.buyer.repository.BuyerRepositoryAddress;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDto;
import com.sashel.user_profile_service.buyer.entity.BuyerEntityAddress;
import com.sashel.user_profile_service.buyer.mapper.BuyerMapperAddress;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuyerServiceAddress {

    @Autowired
    BuyerRepositoryAddress buyerRepositoryAddress;

    // TODO: Return paginated list of BuyerEntity Addresses
    public List<BuyerResponseDtoAddress> getAllBuyerAddresses(String buyerId) {

        List<BuyerEntityAddress> buyerEntityAddresses = buyerRepositoryAddress.findByBuyerId(buyerId);

        List<BuyerResponseDtoAddress> buyerResponseDtoAddresses = new ArrayList<>(buyerEntityAddresses.size());
        for(BuyerEntityAddress e : buyerEntityAddresses) {
            buyerResponseDtoAddresses.add(
                    BuyerMapperAddress.mapBuyerEntityAddressToBuyerResponseDtoAddress(e)
            );
        }

        return buyerResponseDtoAddresses;
    }

    // TODO: to create buyer's address
    public BuyerResponseDtoAddress createBuyerAddress(String buyerId, BuyerRequestDtoAddress buyerRequestDtoAddress) {

        var newBuyerEntityAddress = BuyerMapperAddress.mapBuyerRequestDtoAddressToBuyerEntityAddress(buyerRequestDtoAddress);
        newBuyerEntityAddress.setBuyerId(buyerId);

        // TODO: to check if address already exists
        //       if exists, don't mark new address as default
        //       if not exists, mark new address as default

        List<BuyerEntityAddress> buyerEntityAddresses = buyerRepositoryAddress.findByBuyerId(buyerId);
        if(buyerEntityAddresses.isEmpty()) {
            newBuyerEntityAddress.setIsPrimary(true);
        }

        var savedBuyerEntityAddress = buyerRepositoryAddress.save(newBuyerEntityAddress);
        return BuyerMapperAddress.mapBuyerEntityAddressToBuyerResponseDtoAddress(savedBuyerEntityAddress);
    }

    // TODO: to update a particular buyer's address
    public BuyerResponseDtoAddress updateBuyerAddress(String buyerId, Long addressId, BuyerRequestDtoAddress buyerRequestDtoAddress) {

        var buyerEntityAddress = buyerRepositoryAddress.findById(addressId);
        if(buyerEntityAddress.isPresent()) {
            var updatedBuyerEntityAddress = BuyerMapperAddress.mapBuyerEntityAddressWithBuyerRequestDtoAddress(buyerEntityAddress.get(), buyerRequestDtoAddress);
            var savedBuyerEntityAddress = buyerRepositoryAddress.save(updatedBuyerEntityAddress);
            return BuyerMapperAddress.mapBuyerEntityAddressToBuyerResponseDtoAddress(savedBuyerEntityAddress);
        } else {
            throw new EntityNotFoundException("Address not found");
        }

    }

    // TODO: to delete a particular buyer's address
    public void deleteBuyerAddress(String buyerId, Long addressId) {

        var buyerEntityAddress = buyerRepositoryAddress.findById(addressId);
        if(buyerEntityAddress.isPresent()) {
            buyerRepositoryAddress.delete(buyerEntityAddress.get());
        } else {
            throw new EntityNotFoundException("Address not found");
        }
    }
}

