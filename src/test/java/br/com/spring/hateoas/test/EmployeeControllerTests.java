package br.com.spring.hateoas.test;

import br.com.spring.hateoas.config.EmployeeRepresentationModelAssembler;
import br.com.spring.hateoas.controller.EmployeeController;
import br.com.spring.hateoas.model.Employee;
import br.com.spring.hateoas.model.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@Import({ EmployeeRepresentationModelAssembler.class })
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    public void getShouldFetchAHalDocument() throws Exception {

        given(repository.findAll()).willReturn( //
                Arrays.asList( //
                        new Employee(1L, "Frodo", "Baggins", "ring bearer"), //
                        new Employee(2L, "Bilbo", "Baggins", "burglar")));

        mvc.perform(get("/employees").accept(MediaTypes.HAL_JSON_VALUE)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$._embedded.employees[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.employees[0].firstName", is("Frodo")))
                .andExpect(jsonPath("$._embedded.employees[0].lastName", is("Baggins")))
                .andExpect(jsonPath("$._embedded.employees[0].role", is("ring bearer")))
                .andExpect(jsonPath("$._embedded.employees[0]._links.self.href", is("http://localhost/employees/1")))
                .andExpect(jsonPath("$._embedded.employees[0]._links.employees.href", is("http://localhost/employees")))
                .andExpect(jsonPath("$._embedded.employees[1].id", is(2)))
                .andExpect(jsonPath("$._embedded.employees[1].firstName", is("Bilbo")))
                .andExpect(jsonPath("$._embedded.employees[1].lastName", is("Baggins")))
                .andExpect(jsonPath("$._embedded.employees[1].role", is("burglar")))
                .andExpect(jsonPath("$._embedded.employees[1]._links.self.href", is("http://localhost/employees/2")))
                .andExpect(jsonPath("$._embedded.employees[1]._links.employees.href", is("http://localhost/employees")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/employees"))) //
                .andReturn();
    }
}
