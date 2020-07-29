package com.api.rest.converter;

import com.api.rest.dto.request.PhonesRqDTO;
import com.api.rest.entity.UserPhone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserPhoneRqConverter implements Converter<PhonesRqDTO, UserPhone> {
    @Override
    public UserPhone convert(final PhonesRqDTO source) {
        return new UserPhone(source.getNumber(),
                source.getCityCode(),
                source.getCountryCode());
    }
}
