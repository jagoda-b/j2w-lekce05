package cz.czechitas.java2webapps.lekce5.people;

import cz.czechitas.java2webapps.lekce5.entity.Gender;
import cz.czechitas.java2webapps.lekce5.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Filip Jirsák
 */
@Controller
public class FamousPeopleController {
    public static final String REDIRECT = "redirect:/";
    private final FamousPeopleService service;

    public FamousPeopleController(FamousPeopleService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("people", service.getAll());
        result.addObject("gender", Gender.values());
        return result;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView result = new ModelAndView("detail");
        result.addObject("person", service.getById(id));
        result.addObject("gender", Gender.values());
        return result;
    }

    @PostMapping("/")
    public String append(Person person) {
        service.append(person);
        return REDIRECT;
    }

    @GetMapping("/search")
    public ModelAndView search(String query) {
        ModelAndView result = new ModelAndView("index");
        result.addObject("people", service.getByName(query));
        result.addObject("gender", Gender.values());
        result.addObject("query", query);
        return result;
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return REDIRECT;
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable int id, Person person) {
        service.update(id, person);
        return REDIRECT + id;
    }

}
