package com.app.services;

import com.app.entites.Category;
import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import com.app.payloads.CouponResponse;
import org.springframework.data.domain.Pageable;


public interface CouponService {
    CouponDTO createCoupon( CouponDTO couponDTO);
    CouponResponse<CouponDTO> getAllCoupons(int pageNumber, int pageSize, String sortBy, String sortOrder);
    CouponDTO updateCoupon(Long couponId, CouponDTO couponDTO);
    String deleteCoupon(Long couponId);
}