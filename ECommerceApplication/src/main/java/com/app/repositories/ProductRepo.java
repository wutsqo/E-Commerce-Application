package com.app.repositories;

import com.app.entites.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Category;
import com.app.entites.Product;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	Page<Product> findByProductNameLike(String keyword, Pageable pageDetails);
  Page<Product> findByCategory(Category category, Pageable pageDetails);
    Page<Product> findByBrand(Brand brand, Pageable pageable);
    List<Product> findByBrand(Brand brand);

}

