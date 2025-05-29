package com.juanba.the_sales_galleon.product.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    // ? Se puede implementar de mejor forma en el repository,
    // ? lo dejo aca para mostrar el uso de interface java :D
    ResponseEntity<?> findByActive();
}
