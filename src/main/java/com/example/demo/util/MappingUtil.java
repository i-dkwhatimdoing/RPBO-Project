package com.example.demo.util;

import com.example.demo.domain.dto.ObschepitRequest;
import com.example.demo.domain.dto.ObschepitDto;
import com.example.demo.domain.entity.Obschepit;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class MappingUtil {

    public static ObschepitDto mapToProductDto(Obschepit product) {
        return ObschepitDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .count(product.getCount())
                .requestTime(LocalDateTime.now())
                .build();
    }

    public static Obschepit mapToProductFromRequest(ObschepitRequest request) {
        return Obschepit.builder()
                .name(request.getName())
                .count(request.getCount())
                .price(request.getPrice())
                .build();
    }
}
