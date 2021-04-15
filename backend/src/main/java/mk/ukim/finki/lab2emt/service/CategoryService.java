package mk.ukim.finki.lab2emt.service;



import mk.ukim.finki.lab2emt.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(String name);

    Category update(String name);

    void delete(String name);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);

}
