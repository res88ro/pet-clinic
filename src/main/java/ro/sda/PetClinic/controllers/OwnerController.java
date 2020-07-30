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
import ro.sda.PetClinic.model.Owner;
import ro.sda.PetClinic.model.Pet;
import ro.sda.PetClinic.repository.OwnerRepository;

import javax.validation.Valid;


@Controller
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owners")
    public String getAllOwners(Model model) {
        Iterable<Owner> allOwners = ownerRepository.findAll();
        System.out.println(allOwners);
        model.addAttribute("allOwners", allOwners);
        return "owners";
    }

    @GetMapping("/owners/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        ownerRepository.delete(owner);
        return "redirect:/owners";
    }

    @GetMapping("/owners/update/{id}")
    public String goToEditPage(@PathVariable Long id, Model model) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        model.addAttribute("owner", owner);
        return "owner";
    }

    @GetMapping("/owners/owner.html")
    public String goToEditPage(@ModelAttribute Owner owner) {
        return "owner";
    }

    @PostMapping("/owners/save/{id}")
    public String saveOwner(@Valid Owner owner, @PathVariable(required = false) Long id, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "owner";
        } else {
            ownerRepository.save(owner);
            return "redirect:/owners";
        }
    }
    @PostMapping("/owners/save")
    public String saveOwner(@Validated Owner owner, BindingResult bindingResult, Model model) {
        return saveOwner(owner, null, bindingResult, model);
    }

}




