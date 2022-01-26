package com.nicoardizzoli.springbootmongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document //esto es para mongoDB
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Adress adress;
    private List<String> favouritesSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;


    public Student(String firstName, String lastName, String email, Gender gender, Adress adress, List<String> favouritesSubjects, BigDecimal totalSpentInBooks, LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.adress = adress;
        this.favouritesSubjects = favouritesSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && gender == student.gender && Objects.equals(adress, student.adress) && Objects.equals(favouritesSubjects, student.favouritesSubjects) && Objects.equals(totalSpentInBooks, student.totalSpentInBooks) && Objects.equals(created, student.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, gender, adress, favouritesSubjects, totalSpentInBooks, created);
    }
}
