package ru.nikita.crudcontrollervalidation.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nikita.crudcontrollervalidation.models.SimpleUser;

@Component
public class SimpleUserBodyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SimpleUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SimpleUser simpleUserForValidation = (SimpleUser) target;
        if (simpleUserForValidation.getAge() == 0) {
            errors.rejectValue("age", "Age can't be zero.");
        }
        if (StringUtils.isEmpty(simpleUserForValidation.getName())) {
            errors.rejectValue("name", "Name can't be empty.");
        }
    }
}
