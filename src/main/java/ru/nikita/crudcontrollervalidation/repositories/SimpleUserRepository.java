package ru.nikita.crudcontrollervalidation.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.nikita.crudcontrollervalidation.models.SimpleUser;

import java.util.UUID;

public interface SimpleUserRepository extends ReactiveMongoRepository<SimpleUser, UUID> {
}
