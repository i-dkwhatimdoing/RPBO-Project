package com.example.demo.service;

import com.example.demo.domain.dto.ObschepitRequest;
import com.example.demo.domain.dto.ObschepitDto;
import com.example.demo.domain.entity.Obschepit;
import com.example.demo.domain.exceptions.ProductAlreadyExistException;
import com.example.demo.domain.exceptions.ProductNotFoundException;
import com.example.demo.repository.ObschepitRepository;
import com.example.demo.service.interfaces.ObschepitService;
import com.example.demo.util.MappingUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.demo.util.MappingUtil.mapToProductDto;
import static com.example.demo.util.MappingUtil.mapToProductFromRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObschepitServiceImpl implements ObschepitService {

    private final ObschepitRepository obschepitRepository;

    @Override
    public ObschepitDto getProductByName(String name) throws ProductNotFoundException {
        return obschepitRepository.findByName(name).stream()
                .map(MappingUtil::mapToProductDto)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(name));
    }

    @Override
    @Transactional
    public ObschepitDto createProduct(ObschepitRequest request) throws ProductAlreadyExistException {
        if (!obschepitRepository.existsByName(request.getName())) {
            return mapToProductDto(obschepitRepository.save(mapToProductFromRequest(request)));
        }
        throw new ProductAlreadyExistException(request.getName());
    }



    @Override
    public List<Obschepit> getAllProducts() {
        return obschepitRepository.getAll();
    }

    @Override
    @Transactional
    public ObschepitDto updateProduct(Obschepit product) {
        return mapToProductDto(obschepitRepository.save(product));
    }

    @Override
    public void deleteProduct(String productId) {
        obschepitRepository.deleteById(UUID.fromString(productId));
    }
}
