package mk.ukim.finki.lab2emt.service;



import mk.ukim.finki.lab2emt.model.Product;
import mk.ukim.finki.lab2emt.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, Integer availableCopies, Long category, Long author);

    Optional<Product> save(ProductDto productDto);

    Optional<Product> edit(Long id, String name, Integer availableCopies, Long category, Long author);

    Optional<Product> edit(Long id, ProductDto productDto);

    void markAsTaken(Long id);

    void deleteById(Long id);
}
