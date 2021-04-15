package mk.ukim.finki.lab2emt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer availableCopies;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Author author;

    public Product() {
    }

    public Product(String name, Integer availableCopies, Category category, Author author) {
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }
}
