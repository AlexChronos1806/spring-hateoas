package br.com.spring.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;

@SpringBootApplication
public class SpringHateoasMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringHateoasMain.class, args);
    }

    /**
     * Format embedded collections by pluralizing the resource's type.
     *
     * @return
     */
    @Bean
    EvoInflectorLinkRelationProvider relProvider() {
        return new EvoInflectorLinkRelationProvider();
    }
}
