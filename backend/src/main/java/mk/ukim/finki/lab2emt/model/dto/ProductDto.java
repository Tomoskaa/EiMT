package mk.ukim.finki.lab2emt.model.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private Integer availableCopies;

    private Long category;

    private Long author;

    public ProductDto() {
    }

    public ProductDto(String name, Integer availableCopies, Long category, Long author) {
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }
}
