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
import ro.sda.PetClinic.model.Vet;
import ro.sda.PetClinic.repository.VetRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
public class VetController {

    @Autowired
     VetRepository vetRepository;

    @GetMapping("/vets")
    public String getAllVets(Model model) {
        List<Vet> allVets = vetRepository.findAll();
        System.out.println(allVets);
        model.addAttribute("allVets", allVets);
        return "vets";
    }

    @GetMapping("/vets/delete/{id}")
    public String deleteVet(@PathVariable Long id) {
        Vet vet = vetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        vetRepository.delete(vet);
        return "redirect:/vets";
    }

    @GetMapping("/vets/update/{id}")
    public String goToEditPage(@PathVariable Long id, Model model) {
        Vet vet = vetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        model.addAttribute("vet", vet);
        return "vet";
    }

    @GetMapping("/vets/vet.html")
    public String goToEditPage(@ModelAttribute Vet vet) {
        return "vet";
    }

    @PostMapping("/vets/save/{id}")
    public String saveVet(@Valid Vet vet, @PathVariable(required = false) Long id, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "vet";
        } else {
            vetRepository.save(vet);
            return "redirect:/vets";
        }
    }
    @PostMapping("/vets/save")
    public String saveVet(@Validated Vet vet, BindingResult bindingResult, Model model) {
        return saveVet(vet, null, bindingResult, model);
    }

}




