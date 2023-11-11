package com.example.demo.api.rest;

import com.example.demo.domain.dto.ObschepitRequest;
import com.example.demo.domain.dto.ObschepitDto;
import com.example.demo.domain.entity.Obschepit;
import com.example.demo.domain.exceptions.ProductAlreadyExistException;
import com.example.demo.domain.exceptions.ProductNotFoundException;
import com.example.demo.service.interfaces.ObschepitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequiredArgsConstructor
public class ObschepitController {

    private final ObschepitService obschepitService;

    @GetMapping("/products/all")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<List<Obschepit>> getAllProducts() {
        return ResponseEntity.ok(obschepitService.getAllProducts());
    }

    @GetMapping("/products/find/{name}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<ObschepitDto> getProductByName(@PathVariable String name) throws ProductNotFoundException {
        return ResponseEntity.ok(obschepitService.getProductByName(name));
    }

    @PostMapping("/products")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity<ObschepitDto> createProduct(@RequestBody ObschepitRequest product) throws ProductAlreadyExistException {
        return ResponseEntity.ok(obschepitService.createProduct(product));
    }

    @PatchMapping("/products")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity<ObschepitDto> updateProduct(@RequestBody Obschepit product) {
        return ResponseEntity.ok(obschepitService.updateProduct(product));
    }

    @DeleteMapping("/products")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity deleteProduct(@RequestBody String productId) {
        obschepitService.deleteProduct(productId);
        return ResponseEntity.ok()
                .body(format("product with id= %s deleted", productId));
    }
}
