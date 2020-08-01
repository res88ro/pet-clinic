package ro.sda.PetClinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.PetClinic.model.Pet;
import ro.sda.PetClinic.model.Visit;
import ro.sda.PetClinic.repository.PetRepository;
import ro.sda.PetClinic.repository.VisitRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
public class VisitController {
    @Autowired
    VisitRepository visitRepository;
    @Autowired
    PetRepository petRepository;

    @GetMapping("/visits")
    public String getAllVisits(Model model) {
        Iterable<Visit> allVisits = visitRepository.findAll();
        System.out.println(allVisits);
        model.addAttribute("allVisits", allVisits);
        return "visits";
    }

    @GetMapping("/visits/delete/{id}")
    public String deleteVisit(@PathVariable Long id) {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        visitRepository.delete(visit);
        return "redirect:/visits";
    }

    @GetMapping("/visits/update/{id}")
    public String goToEditPage(@PathVariable Long id, Model model) {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        model.addAttribute("visit", visit);
        return "visit";
    }

    @GetMapping("/visits/visit.html")
    public String goToEditPage(@ModelAttribute Visit visit) {
        return "visit";
    }

    @PostMapping("/visits/save/{id}")
    public String saveVisit(@Valid Visit visit, @PathVariable(required = false) Long id, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "visit";
        } else {
            visitRepository.save(visit);
            return "redirect:/visits";
        }
    }
    @PostMapping("/visits/save")
    public String saveVisit(@Validated Visit visit, BindingResult bindingResult, Model model) {
        return saveVisit(visit, null, bindingResult, model);
    }

    @GetMapping("/visits/{id}/pets")
    public String getPets(@PathVariable Long id, Model model) {

        Visit visit = visitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No owner found with id: " + id));

        List<Pet> assignedPets = petRepository.findByVisit(visit);
        List<Pet> unassignedPets = petRepository.findByVisit(null);

        System.out.println("assignedPets: " + assignedPets);
        System.out.println("unassignedPets: " + unassignedPets);

        model.addAttribute("visit", visit);
        model.addAttribute("assignedPets", assignedPets);
        model.addAttribute("unassignedPets", unassignedPets);

        return "assignVisits";
    }

}
