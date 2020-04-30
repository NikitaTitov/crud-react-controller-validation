package ru.nikita.crudcontrollervalidation.routes;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.nikita.crudcontrollervalidation.handlers.impl.SimpleUserCreateHandler;
import ru.nikita.crudcontrollervalidation.handlers.impl.SimpleUserGetHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SimpleUserRoutes {

    SimpleUserCreateHandler simpleUserCreateHandler;
    SimpleUserGetHandler simpleUserGetHandler;

    @Bean
    public RouterFunction<ServerResponse> handleSimpleUserRoutes() {
        return route(GET("/simpleUser/{uuid}"), simpleUserGetHandler::handleGetRequest)
                .andRoute(POST("/simpleUser/"), simpleUserCreateHandler::handleRequest);
    }
}
