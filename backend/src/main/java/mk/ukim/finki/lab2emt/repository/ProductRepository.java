package mk.ukim.finki.lab2emt.repository;

import mk.ukim.finki.lab2emt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    Optional<Product> findByAvailableCopies(Integer availableCopies);

    void deleteByName(String name);

    void deleteByAvailableCopies(Integer availableCopies);
}
