
package ro.sda.PetClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.PetClinic.model.Owner;

import java.util.Optional;


public interface OwnerRepository extends JpaRepository<Owner, Long> {



}
