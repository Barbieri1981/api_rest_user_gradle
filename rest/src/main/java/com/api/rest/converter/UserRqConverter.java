package com.api.rest.converter;

import com.api.rest.dto.request.UserRqDTO;
import com.api.rest.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRqConverter implements Converter<UserRqDTO, User> {

    @Override
    public User convert(final UserRqDTO source) {
        return new User(source.getName(),
                source.getEmail(),
                source.getPassword(),
                true);
    }
}
