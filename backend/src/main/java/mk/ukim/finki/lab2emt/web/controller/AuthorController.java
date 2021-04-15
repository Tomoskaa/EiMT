package mk.ukim.finki.lab2emt.web.controller;

import mk.ukim.finki.lab2emt.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getCategoryPage(Model model){
        model.addAttribute("authors", this.authorService.findAll());
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }
}
