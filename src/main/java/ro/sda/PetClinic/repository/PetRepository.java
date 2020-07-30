
package ro.sda.PetClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.PetClinic.model.Pet;


public interface PetRepository extends JpaRepository<Pet, Long> {


}
