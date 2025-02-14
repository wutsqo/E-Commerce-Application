package com.app.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import com.app.payloads.CouponResponse;
import com.app.repositories.CouponRepo;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepo couponRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        Coupon existingCoupon = couponRepo.findByCouponCode(couponDTO.getCouponCode());

        if (existingCoupon != null) {
            throw new APIException("Coupon with the code '" + couponDTO.getCouponCode() + "' already exists!");
        }

        Coupon coupon = modelMapper.map(couponDTO, Coupon.class);
        Coupon savedCoupon = couponRepo.save(coupon);
        return modelMapper.map(savedCoupon, CouponDTO.class);
    }

    @Override
    public CouponResponse<CouponDTO> getAllCoupons(int pageNumber, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Coupon> couponPage = couponRepo.findAll(pageable);
        List<Coupon> coupons = couponPage.getContent();

        if (coupons.isEmpty()) {
            throw new APIException("No coupons have been created yet.");
        }

        List<CouponDTO> couponDTOs = coupons.stream()
                .map(coupon -> modelMapper.map(coupon, CouponDTO.class))
                .collect(Collectors.toList());

        return new CouponResponse<>(
                couponDTOs,
                couponPage.getNumber(),
                couponPage.getSize(),
                couponPage.getTotalElements(),
                couponPage.getTotalPages(),
                couponPage.isLast()
        );
    }

    @Override
    public CouponDTO updateCoupon(Long couponId, CouponDTO couponDTO) {
        Coupon savedCoupon = couponRepo.findById(couponId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));

        savedCoupon.setCouponCode(couponDTO.getCouponCode());
        savedCoupon.setDiscountAmount(couponDTO.getDiscountAmount());
        savedCoupon = couponRepo.save(savedCoupon);
        return modelMapper.map(savedCoupon, CouponDTO.class);
    }

    @Override
    public String deleteCoupon(Long couponId) {
        Coupon coupon = couponRepo.findById(couponId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));

        couponRepo.delete(coupon);

        return "Coupon with couponId: " + couponId + " deleted successfully !!!";
    }
}
