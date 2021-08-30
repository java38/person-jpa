package telran.java38.person.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java38.person.model.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
					include = JsonTypeInfo.As.PROPERTY,
					property = "type")
@JsonSubTypes({
	@Type(value = ChildDto.class, name = "child"),
	@Type(value = EmployeeDto.class, name = "employee"),
	@Type(value = PersonDto.class, name = "person")
})
public class PersonDto {
	Integer id;
	String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate birthDate;
	Address address;

}
