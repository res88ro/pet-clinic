package ro.sda.PetClinic.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

	@Column
    @Enumerated(EnumType.STRING)
    private Specialty specialty;




}


