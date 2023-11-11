package com.example.demo.service.interfaces;

import com.example.demo.domain.dto.ObschepitRequest;
import com.example.demo.domain.dto.ObschepitDto;
import com.example.demo.domain.entity.Obschepit;
import com.example.demo.domain.exceptions.ProductAlreadyExistException;
import com.example.demo.domain.exceptions.ProductNotFoundException;

import java.util.List;

public interface ObschepitService {
    ObschepitDto getProductByName(String name) throws ProductNotFoundException;

    ObschepitDto createProduct(ObschepitRequest product) throws ProductAlreadyExistException;

    List<Obschepit> getAllProducts();

    ObschepitDto updateProduct(Obschepit product);

    void deleteProduct(String productId);
}
