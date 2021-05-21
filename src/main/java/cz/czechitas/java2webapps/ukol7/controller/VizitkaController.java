package cz.czechitas.java2webapps.ukol7.controller;

import cz.czechitas.java2webapps.ukol7.entity.Vizitka;
import cz.czechitas.java2webapps.ukol7.reporitory.VizitkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class VizitkaController {

    private final VizitkaRepository repository;

    @Autowired
    public VizitkaController (VizitkaRepository repository) {
        this.repository = repository;
    }

    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public Object seznam() {

        return new ModelAndView("seznam")
               .addObject("vizitky", repository.findAll());
    }

    @GetMapping("/detail{id:[0-9]+}")
    public Object detail(@PathVariable long id) {
        Optional<Vizitka> vizitka = repository.findById(id);
        if (vizitka.isPresent()) {
            return new ModelAndView("vizitka")
                    .addObject("vizitka", vizitka.get());
        } else {
            return null;
        }
    }

    @GetMapping("/nova")
    public Object nova() {
        return new ModelAndView("formular")
                .addObject("vizitka", new Vizitka());
    }

    @PostMapping("/nova")
    public Object nova(@ModelAttribute("vizitka") @Valid Vizitka vizitka, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formular";
        }
        repository.save(vizitka);
        return "redirect:/";
    }
}
