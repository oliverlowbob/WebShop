package com.example.webshop.controller;

import com.example.webshop.Model.Product;
import com.example.webshop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// annotation for controller
@Controller
public class HomeController {

    //dependency injection af PersonService
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productService.fetchtAllProducts());
        return "index";
    }


    //route for /persondata - view model Model bruges til at få data til og fra view og browser
    @GetMapping("/productdata")
    public String productData(Model model){
        // tilføj person collection til model atribut persons
        model.addAttribute("products", productService.fetchtAllProducts());
        // vis view persondata
        return "productdata";
    }

    @GetMapping("/create")
    public String showCreate(){
        return "create";
    }

    //route for at oprette - bruges når der trykkes submit/Opret - henter first_name og last_name via Person
    @PostMapping("/create")
    public String create(@ModelAttribute Product product){
        // tilføj person vha. add service
        productService.addProduct(product);
        // sikr mod refresh problem
        return "redirect:/";
    }

    // få fat id fra stien vha. @PathVariable
    @GetMapping("/delete/{iden}")
    public String delete(@PathVariable("iden") int id){
        //kald deleteservice med id
        productService.deleteProduct(id);
        //sikr mod refresh fejl og sletter igen
        return "redirect:/";
    }

    // få fat id fra stien vha. @PathVariable
    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model){
        //tilføj person med id til viewmodel
        model.addAttribute("products", productService.findProductById(id));
        return "update";
    }

    // opdater person - @ModelAttribute bruges til at få fat i person fra post
    @PostMapping("/update")
    public String updateDoIt(@ModelAttribute Product product){
        //kald update service
        productService.updateProduct(product);
        //sikr mod refresh fejl og sletter igen
        return "redirect:/";
    }
}
