package ru.nikita.crudcontrollervalidation.services;

import reactor.core.publisher.Mono;
import ru.nikita.crudcontrollervalidation.models.SimpleUser;

import java.util.UUID;

public interface SimpleUserService {

    Mono<SimpleUser> getSimpleUser(UUID simpleUserId);

    Mono<SimpleUser> createSimpleUser(SimpleUser simpleUser);

    UUID deleteSimpleUser(UUID simpleUserId);
}
