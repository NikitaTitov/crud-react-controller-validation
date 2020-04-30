package ru.nikita.crudcontrollervalidation.configs.mockup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.nikita.crudcontrollervalidation.models.SimpleUser;
import ru.nikita.crudcontrollervalidation.repositories.SimpleUserRepository;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MockDataInitializer {

    private final SimpleUserRepository simpleUserRepository;

    @PostConstruct
    public void createInitData() {
        SimpleUser mockup = new SimpleUser();
        mockup.setId(UUID.randomUUID());
        mockup.setAge(19);
        mockup.setName("Test");

        simpleUserRepository
                .deleteAll()
                .then(simpleUserRepository.save(mockup))
                .subscribe(simpleUser -> log.error("User created with id " + simpleUser.getId()));
    }
}
