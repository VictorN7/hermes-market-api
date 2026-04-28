package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.request.BrandRequest;
import com.hermes.market.application.dto.response.BrandDetailResponse;
import com.hermes.market.application.dto.response.BrandMenuResponse;
import com.hermes.market.domain.product.Brand;


import java.util.List;
public class BrandMapper {


    private BrandMapper(){
    }

    public static BrandMenuResponse toMenu(Brand brand){
        return new BrandMenuResponse(brand.getId(), brand.getName());
    }

    public static BrandDetailResponse toResponse(Brand brand){
        return new BrandDetailResponse(brand.getId(), brand.getName(), brand.getStatus().name(), brand.getCreatedAt());
    }

    public static Brand toCreate(BrandRequest brandRequest){
        return new Brand(brandRequest.getName().trim());
    }
}
