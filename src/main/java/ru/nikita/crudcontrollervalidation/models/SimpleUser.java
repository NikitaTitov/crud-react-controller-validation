package ru.nikita.crudcontrollervalidation.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Data
@Document(value = "Client")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleUser {

    @Id
    UUID id;

    int age;

    String name;

}
