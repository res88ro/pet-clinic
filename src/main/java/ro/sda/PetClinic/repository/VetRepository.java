
package ro.sda.PetClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.PetClinic.model.Vet;


public interface VetRepository extends JpaRepository<Vet, Long> {

}
