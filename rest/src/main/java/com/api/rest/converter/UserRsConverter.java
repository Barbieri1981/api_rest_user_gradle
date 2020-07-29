package com.api.rest.converter;

import com.api.rest.dto.response.UserRsDTO;
import com.api.rest.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRsConverter implements Converter<User, UserRsDTO> {

    @Override
    public UserRsDTO convert(final User source) {
        return new UserRsDTO(source.getId(),
                source.getCreateDate(),
                source.getModifiedDate(),
                source.getLastLogin(),
                source.isActive(),
                source.getUserToken());

    }
}
