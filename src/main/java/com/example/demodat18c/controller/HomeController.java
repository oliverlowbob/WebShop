package com.example.demodat18c.controller;

import com.example.demodat18c.Model.Person;
import com.example.demodat18c.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String index2(Model model){
        String fname = "Bruce";
        String lname = "Wayne";
        model.addAttribute("first_name", fname);
        model.addAttribute("last_name", lname);
        return "index2";
    }

    @GetMapping("/persondata")
    public String personData(Model model){

        model.addAttribute("persons", personService.fetchAllPersons());
        return "persondata";
    }

    @GetMapping("/inputside")
    public String inputSide(){
        return "inputside";
    }

    @PostMapping("/outputside")
    public String outputSide(WebRequest wr, Model model){
        String fornavn = wr.getParameter("first_name");
        String efternavn = wr.getParameter("last_name");

        Person myPerson = new Person(fornavn, efternavn);

        model.addAttribute("person", myPerson);

        return "persondata";

    }

}
