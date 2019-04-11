package com.example.demodat18c.controller;

import com.example.demodat18c.Model.Person;
import com.example.demodat18c.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

// annotation for controller
@Controller
public class HomeController {

    //dependency injection af PersonService
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

    //route for /persondata - view model Model bruges til at få data til og fra view og browser
    @GetMapping("/persondata")
    public String personData(Model model){
        // tilføj person collection til model atribut persons
        model.addAttribute("persons", personService.fetchAllPersons());
        // vis view persondata
        return "persondata";
    }

    @GetMapping("/create")
    public String showCreate(){
        return "create";
    }

    //route for at oprette - bruges når der trykkes submit/Opret - henter first_name og last_name via Person
    @PostMapping("/create")
    public String create(@ModelAttribute Person person){
        // tilføj person vha. add service
        personService.addPerson(person);
        // sikr mod refresh problem
        return "redirect:/persondata";
    }

    // få fat id fra stien vha. @PathVariable
    @GetMapping("/delete/{iden}")
    public String delete(@PathVariable("iden") int id){
        //kald deleteservice med id
        personService.deletePerson(id);
        //sikr mod refresh fejl og sletter igen
        return "redirect:/persondata";
    }

    // få fat id fra stien vha. @PathVariable
    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model){
        //tilføj person med id til viewmodel
        model.addAttribute("person", personService.findPersonById(id));
        return "update";
    }

    // opdater person - @ModelAttribute bruges til at få fat i person fra post
    @PostMapping("/update")
    public String updateDoIt(@ModelAttribute Person person){
        //kald update service
        personService.updatePerson(person);
        //sikr mod refresh fejl og sletter igen
        return "redirect:/persondata";
    }

    /*
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
    */
}
