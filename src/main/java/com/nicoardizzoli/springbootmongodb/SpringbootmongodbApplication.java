package com.nicoardizzoli.springbootmongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootmongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmongodbApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
        return args -> {
            Student student = new Student(
                    "Nicolas",
                    "ardizzoli",
                    "nardizzoli@gmail.com",
                    Gender.MALE,
                    new Adress("Argentina", "Buenos aires", "1900"),
                    List.of("Computer Science"),
                    BigDecimal.TEN,
                    LocalDateTime.now());

            //BUSCANDO Y HACIENDO QUERYS CON MONGO TEMPLATE
            usingMongoTemplateAndQuery(mongoTemplate);

            usingMongoRepository(studentRepository, student);
        };


    }

    private void usingMongoRepository(StudentRepository studentRepository, Student student) {
        //BUSCANDO CON EL REPOSITORY EL CUAL TIENE UN MONTON DE METODOS PARA UTILIZAR.
        studentRepository.findStudentByEmail("nardizzoli@gmail.com").ifPresentOrElse((s -> {
            System.out.println(s + "already exist");
        }), () -> {
            System.out.println("insterting student" + student);
            studentRepository.insert(student);
        });
    }

    private void usingMongoTemplateAndQuery(MongoTemplate mongoTemplate) {
        //para crear custom querys con MongoTemplate (sin usar el repository)
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is("nardizzoli@gmail.com").and("firstName").is("Nicolas"));

        List<Student> students = mongoTemplate.find(query, Student.class);
    }
}
