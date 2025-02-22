package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.entities.People;
import dev.patricksilva.crud.models.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleViewController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping
    public String listPeople(Model model) {
        model.addAttribute("people", peopleService.getAllPeople());
        return "people-list";
    }

    @GetMapping("/{id}")
    public String viewPerson(@PathVariable("id") Long id, Model model) {
        People person = peopleService.getPeopleById(id);
        if (person == null) {
            throw new IllegalArgumentException("Invalid person Id:" + id);
        }
        model.addAttribute("person", person);
        return "person-view";
    }

    @GetMapping("/new")
    public String showNewPersonForm(Model model) {
        model.addAttribute("person", new People());
        return "person-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditPersonForm(@PathVariable("id") Long id, Model model) {
        People person = peopleService.getPeopleById(id);
        if (person == null) {
            throw new IllegalArgumentException("Invalid person Id:" + id);
        }
        model.addAttribute("person", person);
        return "person-form";
    }

    @PostMapping
    public String savePerson(@ModelAttribute("person") People person) {
        peopleService.savePeople(person);
        return "redirect:/people";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        peopleService.deletePeople(id);
        return "redirect:/people";
    }
}