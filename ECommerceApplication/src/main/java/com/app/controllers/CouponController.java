package com.app.controllers;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import com.app.payloads.CouponResponse;
import com.app.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.AppConstants;
import com.app.entites.Category;
import com.app.payloads.CategoryDTO;
import com.app.payloads.CouponDTO;
import com.app.services.CouponService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/admin/coupons")
    public ResponseEntity<CouponDTO> createCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        CouponDTO savedCouponDTO = couponService.createCoupon(couponDTO);

        return new ResponseEntity<CouponDTO>(savedCouponDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/coupons")
    public ResponseEntity<CouponResponse<CouponDTO>> getCoupons(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "couponCode", required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {

        CouponResponse<CouponDTO> couponResponse = couponService.getAllCoupons(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(couponResponse, HttpStatus.OK);
    }

    @PutMapping("/admin/coupons/{couponId}")
    public ResponseEntity<CouponDTO> updateCoupon(
            @PathVariable Long couponId,
            @Valid @RequestBody CouponDTO couponDTO) {

        CouponDTO updatedCoupon = couponService.updateCoupon(couponId, couponDTO);
        return new ResponseEntity<>(updatedCoupon, HttpStatus.OK);
    }

    @DeleteMapping("/admin/coupons/delete/{couponID}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long couponID) {
        couponService.deleteCoupon(couponID);
        return new ResponseEntity<>("Coupon with ID " + couponID + " has been deleted successfully", HttpStatus.OK);
    }
}