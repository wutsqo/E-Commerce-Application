package com.app.controllers;

import com.app.entites.Brand;
import com.app.payloads.BrandDTO;
import com.app.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        Brand createdBrand = brandService.createBrand(brandDTO);
        BrandDTO createdBrandDto = modelMapper.map(createdBrand, BrandDTO.class);
        return new ResponseEntity<>(createdBrandDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        List<BrandDTO> brandDtos = brands.stream()
                .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(brandDtos, HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId);
        BrandDTO brandDto = modelMapper.map(brand, BrandDTO.class);
        return new ResponseEntity<>(brandDto, HttpStatus.OK);
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long brandId, @RequestBody BrandDTO brandDTO) {
        brandDTO.setBrandId(brandId);
        Brand updatedBrand = brandService.updateBrand(brandDTO);
        BrandDTO updatedBrandDto = modelMapper.map(updatedBrand, BrandDTO.class);
        return new ResponseEntity<>(updatedBrandDto, HttpStatus.OK);
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}