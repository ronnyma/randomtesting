package ai.transfinite.randomtesting.model;

import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;

@Slf4j
class PersonTest {
    Person person;

    @BeforeEach
    void setUp() {
        person = Instancio.of(Person.class)
                .generate(field(Person::firstName), gen -> gen.string().length(2, 15))
                .generate(field(Person::lastName), gen -> gen.string().length(3, 15))
                .generate(field(Person::dateOfBirth), gen -> gen.temporal().localDate().past().min(LocalDate.now().minusYears(80L)))
                .generate(field(Person::city), gen -> gen.oneOf("New York City", "Chicago", "Los Angeles", "Seattle"))
                .create();
    }

    @Test
    void should_createPersonWithNormalValuesForNameAndAge() {
        assertThat(person.firstName()).hasSizeBetween(2, 15);
        assertThat(person.lastName()).hasSizeBetween(3, 15);
        assertThat(person.dateOfBirth()).isBefore(LocalDate.now());
        assertThat(person.dateOfBirth()).isAfter(LocalDate.now().minusYears(80L));
        assertThat(person.city()).containsAnyOf("New York City", "Chicago", "Los Angeles", "Seattle");
        log.info("Person: {}", person);
    }
}