package ru.nikita.crudcontrollervalidation.handlers.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.nikita.crudcontrollervalidation.handlers.AbstractValidatorHandler;
import ru.nikita.crudcontrollervalidation.models.SimpleUser;
import ru.nikita.crudcontrollervalidation.services.SimpleUserService;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SimpleUserCreateHandler extends AbstractValidatorHandler<SimpleUser, Validator> {

    SimpleUserService simpleUserService;

    public SimpleUserCreateHandler(
            @Qualifier("simpleUserBodyValidator") @Autowired Validator validator,
            @Autowired SimpleUserService simpleUserService
    ) {
        super(SimpleUser.class, validator);
        this.simpleUserService = simpleUserService;
    }

    @Override
    protected Mono<ServerResponse> processValidBody(SimpleUser validBody, ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        simpleUserService.createSimpleUser(validBody),
                        SimpleUser.class
                );
    }
}
