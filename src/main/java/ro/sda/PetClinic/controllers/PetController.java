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
import ro.sda.PetClinic.model.Visit;
import ro.sda.PetClinic.repository.OwnerRepository;
import ro.sda.PetClinic.repository.PetRepository;
import ro.sda.PetClinic.repository.VisitRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PetController {


    @Autowired
    PetRepository petRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    VisitRepository visitRepository;

    @GetMapping("/pets")
    public String getAllPets(Model model) {
        List<Pet> allPets = petRepository.findAll();
        System.out.println(allPets);
        model.addAttribute("allPets", allPets);
        return "pets";
    }

    @GetMapping("/pets/delete/{id}")
    public String deletePet(@PathVariable Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        petRepository.delete(pet);
        return "redirect:/pets";
    }

    @GetMapping("/pets/update/{id}")
    public String goToEditPage(@PathVariable Long id, Model model) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        model.addAttribute("pet", pet);
        return "pet";
    }

    @GetMapping("/pets/pet.html")
    public String goToEditPage(@ModelAttribute Pet pet) {
        return "pet";
    }

    @PostMapping("/pets/save/{id}")
    public String savePet(@Valid Pet pet, @PathVariable(required = false) Long id, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "pet";
        } else {
            petRepository.save(pet);
            return "redirect:/pets";
        }
    }

    @PostMapping("/pets/save")
    public String savePet(@Validated Pet pet, BindingResult bindingResult, Model model) {
        return savePet(pet, null, bindingResult, model);
    }

    @GetMapping("/owners/{ownerId}/pets/remove/{petId}")
    public String removePetFromOwner(@PathVariable Long ownerId, @PathVariable Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new IllegalArgumentException("No student found with id: " + petId));
        pet.setOwner(null);
        petRepository.save(pet);
        return "redirect:/owners/" + ownerId + "/pets";
    }

    @GetMapping("/owners/{ownerId}/pets/assign/{petId}")
    public String assignPetToOwner(@PathVariable Long ownerId, @PathVariable Long petId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new IllegalArgumentException("No team found with id: " + ownerId));
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new IllegalArgumentException("No student found with id: " + petId));
        pet.setOwner(owner);
        petRepository.save(pet);
        return "redirect:/owners/" + ownerId + "/pets";
    }




    @GetMapping("/visits/{visitId}/pets/remove/{petId}")
    public String removePetFromVisit(@PathVariable Long visitId, @PathVariable Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new IllegalArgumentException("No student found with id: " + petId));
        pet.setVisit(null);
        petRepository.save(pet);
        return "redirect:/visits/" + visitId + "/pets";
    }

    @GetMapping("/visits/{visitId}/pets/assign/{petId}")
    public String assignPetToVisit(@PathVariable Long visitId, @PathVariable Long petId) {
        Visit visit = visitRepository.findById(petId).orElseThrow(() -> new IllegalArgumentException("No student found with id: " + visitId));
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new IllegalArgumentException("No team found with id: " + petId));
        pet.setVisit(visit);
        petRepository.save(pet);
        return "redirect:/visits/" + petId + "/pets";
    }
}
