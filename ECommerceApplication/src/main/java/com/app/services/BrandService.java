package com.app.services;

import com.app.entites.Brand;
import com.app.payloads.BrandDTO;

import java.util.List;

public interface BrandService {
    Brand createBrand(BrandDTO brandDTO);
    List<Brand> getAllBrands();
    Brand getBrandById(Long brandId);
    Brand updateBrand(BrandDTO brandDTO);
    void deleteBrand(Long brandId);
}