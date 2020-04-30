package ru.nikita.crudcontrollervalidation.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.nikita.crudcontrollervalidation.models.SimpleUser;
import ru.nikita.crudcontrollervalidation.repositories.SimpleUserRepository;
import ru.nikita.crudcontrollervalidation.services.SimpleUserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleUserServiceImpl implements SimpleUserService {

    private final SimpleUserRepository simpleUserRepository;

    @Override
    public Mono<SimpleUser> getSimpleUser(UUID simpleUserId) {
        return simpleUserRepository.findById(simpleUserId);
    }

    @Override
    public Mono<SimpleUser> createSimpleUser(SimpleUser simpleUser) {
        if (simpleUser.getId() == null) {
            simpleUser.setId(UUID.randomUUID());
        }
        return simpleUserRepository.save(simpleUser);
    }

    @Override
    public UUID deleteSimpleUser(UUID simpleUserId) {
        simpleUserRepository.deleteById(simpleUserId);
        return simpleUserId;
    }
}
