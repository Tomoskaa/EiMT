package mk.ukim.finki.lab2emt.web.controller;

import mk.ukim.finki.lab2emt.model.Author;
import mk.ukim.finki.lab2emt.model.Category;
import mk.ukim.finki.lab2emt.model.Product;
import mk.ukim.finki.lab2emt.service.AuthorService;
import mk.ukim.finki.lab2emt.service.CategoryService;
import mk.ukim.finki.lab2emt.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             AuthorService authorService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("authors", authors);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Integer availableCopies,
            @RequestParam Long category,
            @RequestParam Long author) {
        if (id != null) {
            this.productService.edit(id, name, availableCopies, category, author);
        } else {
            this.productService.save(name, availableCopies, category, author);
        }
        return "redirect:/books";
    }

    @GetMapping("/mark/{id}")
    public String markProduct(@PathVariable Long id, Model model) {
        //if (availableCopies > 0) {
        this.productService.markAsTaken(id);
//        } else {
//            this.productService.deleteById(id);
//        }
        return "redirect:/books";
        //this.productService.markAsTaken(availableCopies);
//        if (this.productService.findById(id).isPresent()) {
//            //Product product = this.productService.findById(id).get();
//            //model.addAttribute("product", product);
//            model.addAttribute("bodyContent", "products");
//            return "master-template";
//        }
//        return "redirect:/products?error=ProductNotFound";
    }
}
