package ai.transfinite.randomtesting.model;


import java.time.LocalDate;

public record Person(String firstName, String lastName, LocalDate dateOfBirth, String city) {
}
