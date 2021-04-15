package mk.ukim.finki.lab2emt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
