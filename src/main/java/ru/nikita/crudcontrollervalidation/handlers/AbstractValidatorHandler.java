package ru.nikita.crudcontrollervalidation.handlers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractValidatorHandler<E, U extends Validator> {

    private final Class<E> validationClass;
    private final U validator;

    protected abstract Mono<ServerResponse> processValidBody(E validBody, ServerRequest request);

    public final Mono<ServerResponse> handleRequest(ServerRequest request) {
        return request.bodyToMono(validationClass)
                .flatMap(body -> {
                    Errors errors = new BeanPropertyBindingResult(body, validationClass.getName());
                    validator.validate(body, errors);
                    if (errors.getAllErrors().isEmpty()) {
                        return processValidBody(body, request);
                    } else {
                        return onValidationErrors(body, errors);
                    }
                });
    }

    public final Mono<ServerResponse> handleGetRequest(ServerRequest request) {
        return Mono.just(
                UUID.fromString(request.pathVariable("uuid"))
        )
                .flatMap(uuid -> {
                    Errors errors = new BeanPropertyBindingResult(uuid, validationClass.getName());
                    validator.validate(uuid, errors);
                    if (errors.getAllErrors().isEmpty()) {
                        return processValidBody((E) uuid, request); //  TODO change logic of handlers. Create separate abstract handler or modify this one
                    } else {
                        return onValidationErrors((E) uuid, errors);
                    }
                });
    }

    protected Mono<ServerResponse> onValidationErrors(E body, Errors errors) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().toString());
    }
}
