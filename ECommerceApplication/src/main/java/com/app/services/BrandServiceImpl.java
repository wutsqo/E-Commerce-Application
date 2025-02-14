package com.app.services;

import com.app.entites.Brand;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.BrandDTO;
import com.app.repositories.BrandRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Brand createBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        return brandRepo.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandRepo.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "brandId", brandId));
    }

    @Override
    public Brand updateBrand(BrandDTO brandDTO) {
        Brand brand = brandRepo.findById(brandDTO.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "brandId", brandDTO.getBrandId()));
        brand.setBrandName(brandDTO.getBrandName());

        return brandRepo.save(brand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        Brand brand = brandRepo.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "brandId", brandId));
        brandRepo.delete(brand);
    }
}