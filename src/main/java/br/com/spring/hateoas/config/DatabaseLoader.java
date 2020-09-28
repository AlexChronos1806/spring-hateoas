package br.com.spring.hateoas.config;

import br.com.spring.hateoas.model.Employee;
import br.com.spring.hateoas.model.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

    /**
     * Use Spring to inject a {@link EmployeeRepository} that can then load data. Since this will run only after the app
     * is operational, the database will be up.
     *
     * @param repository
     */
    @Bean
    CommandLineRunner init(EmployeeRepository repository) {

        return args -> {
            repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
            repository.save(new Employee("Bilbo", "Baggins", "burglar"));
        };
    }

}
