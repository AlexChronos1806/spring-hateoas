package br.com.spring.hateoas.controller;

import br.com.spring.hateoas.config.EmployeeRepresentationModelAssembler;
import br.com.spring.hateoas.model.Employee;
import br.com.spring.hateoas.model.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeRepresentationModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeRepresentationModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * Look up all employees, and transform them into a REST collection resource using
     * {@link EmployeeRepresentationModelAssembler#toCollectionModel(Iterable)}. Then return them through Spring Web's
     * {@link ResponseEntity} fluent API.
     */
    @GetMapping("/employees")
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> findAll() {

        return ResponseEntity.ok( //
                this.assembler.toCollectionModel(this.repository.findAll()));

    }

    /**
     * Look up a single {@link Employee} and transform it into a REST resource using
     * {@link EmployeeRepresentationModelAssembler#toModel(Object)}. Then return it through Spring Web's
     * {@link ResponseEntity} fluent API.
     *
     * @param id
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<EntityModel<Employee>> findOne(@PathVariable long id) {

        return this.repository.findById(id) //
                .map(this.assembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }
}
