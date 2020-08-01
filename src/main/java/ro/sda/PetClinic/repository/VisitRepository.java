
package ro.sda.PetClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.PetClinic.model.Pet;
import ro.sda.PetClinic.model.Visit;

import java.util.List;


public interface VisitRepository extends JpaRepository<Visit, Long> {

    public List<Visit> findByPetId(Pet pet);



}
