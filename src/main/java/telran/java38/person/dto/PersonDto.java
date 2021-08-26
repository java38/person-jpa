package telran.java38.person.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java38.person.model.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
	Integer id;
	String name;
	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate birthDate;
	Address address;

}
