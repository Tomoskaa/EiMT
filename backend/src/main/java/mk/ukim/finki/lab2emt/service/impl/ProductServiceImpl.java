package mk.ukim.finki.lab2emt.service.impl;


import mk.ukim.finki.lab2emt.model.Author;
import mk.ukim.finki.lab2emt.model.Category;
import mk.ukim.finki.lab2emt.model.Product;
import mk.ukim.finki.lab2emt.model.dto.ProductDto;
import mk.ukim.finki.lab2emt.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.lab2emt.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.lab2emt.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.lab2emt.repository.AuthorRepository;
import mk.ukim.finki.lab2emt.repository.CategoryRepository;
import mk.ukim.finki.lab2emt.repository.ProductRepository;
import mk.ukim.finki.lab2emt.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              AuthorRepository authorRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Integer availableCopies, Long categoryId, Long authorId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.productRepository.deleteByName(name);
        Product product = new Product(name, availableCopies, category, author);
        this.productRepository.save(product);
        return Optional.of(product);
        //return Optional.of(this.productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public Optional<Product> save(ProductDto productDto) {
        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Author author = this.authorRepository.findById(productDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(productDto.getAuthor()));

        this.productRepository.deleteByName(productDto.getName());
        Product product = new Product(productDto.getName(), productDto.getAvailableCopies(), category, author);
        this.productRepository.save(product);
        return Optional.of(product);
    }


    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository
                .findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDto.getName());
        product.setAvailableCopies(productDto.getAvailableCopies());

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        product.setCategory(category);

        Author author = this.authorRepository.findById(productDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(productDto.getAuthor()));
        product.setAuthor(author);

        this.productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Integer availableCopies, Long categoryId, Long authorId) {

        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setAvailableCopies(availableCopies);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        product.setAuthor(author);

        this.productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public void markAsTaken(Long id) {
        Product product = productRepository.findById(id).get();
        int total = product.getAvailableCopies();
        if (total > 0) {
            int newTotal = total - 1;
            product.setAvailableCopies(newTotal);
        } else {
            product.setAvailableCopies(0);
            //this.productRepository.deleteById(id);
        }
        this.productRepository.save(product);
    }

//        Product product = this.productRepository.findByAvailableCopies(availableCopies)
//                .orElseThrow(() -> new AvailableCopiesException(availableCopies));
//
//        List<Product> products;
//
//        for(int i = product.getAvailableCopies(); i > 0; i--) {
//            product.setAvailableCopies(availableCopies);
//        }
//
//        if(availableCopies > 0) {
//            this.productRepository.save(product);
//        } else {
//            this.productRepository.deleteByAvailableCopies(availableCopies);
//        }
//        return Optional.of(product);


    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

}
