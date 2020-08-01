
package ro.sda.PetClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.PetClinic.model.Owner;
import ro.sda.PetClinic.model.Pet;
import ro.sda.PetClinic.model.Visit;

import java.util.List;


public interface PetRepository extends JpaRepository<Pet, Long> {

    public List<Pet> findByOwner(Owner owner);
    public List<Pet> findByVisit(Visit visit);


}
