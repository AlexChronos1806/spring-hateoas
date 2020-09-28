package br.com.spring.hateoas.config;

import br.com.spring.hateoas.controller.EmployeeController;
import br.com.spring.hateoas.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Employee> {

    public EmployeeRepresentationModelAssembler() {
        super(EmployeeController.class);
    }
}
