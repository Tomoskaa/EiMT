package mk.ukim.finki.lab2emt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "country_name")
public class Country {

    @Id
    private Long id;

    private String name;

    private String continent;

    public Country() {
    }
}
