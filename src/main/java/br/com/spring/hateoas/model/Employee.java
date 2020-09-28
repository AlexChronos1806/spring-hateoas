package br.com.spring.hateoas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String role;

    /**
     * Useful constructor when id is not yet known.
     *
     * @param firstName
     * @param lastName
     * @param role
     */
    public Employee(String firstName, String lastName, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    /**
     * This method will create another piece of data in the REST resource representation. These types of methods are key
     * in supporting backward compatibility. By NOT removing old fields, and instead replacing them with methods like
     * this, an API can evolve without breaking old clients. Because of {@code @JsonIgnoreProperties} settings above, this
     * attribute will be ignore if sent back to the server, allowing API evolution.
     *
     * @return
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

}
