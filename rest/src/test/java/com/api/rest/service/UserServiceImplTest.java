package com.api.rest.service;

import com.api.rest.converter.TokenConverter;
import com.api.rest.converter.UserPhoneRqConverter;
import com.api.rest.converter.UserRqConverter;
import com.api.rest.converter.UserRsConverter;
import com.api.rest.dto.request.PhonesRqDTO;
import com.api.rest.dto.request.UserRqDTO;
import com.api.rest.dto.response.UserRsDTO;
import com.api.rest.entity.User;
import com.api.rest.exception.UserException;
import com.api.rest.repository.UserPhoneRepository;
import com.api.rest.repository.UserRepository;
import com.api.rest.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Spy
    private UserRqConverter userRqConverter;
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserPhoneRqConverter userPhoneRqConverter;
    @Mock
    private UserPhoneRepository userPhoneRepository;
    @Spy
    private UserRsConverter userRsConverter;
    @Spy
    private TokenConverter tokenConverter;

    private static final String NAME = "testName";
    private static final String EMAIL = "testEmail@dominio.com";
    private static final String PASSWORD = "asdASD123";


    @Before
    public void setup() {
        this.userService = new UserServiceImpl(this.userRqConverter,
                this.userRepository,
                this.userPhoneRqConverter,
                this.userPhoneRepository,
                this.userRsConverter,
                this.tokenConverter);
    }

    @Test
    public void whenCreateUserIsCalledAndEmailIsInvalidThenThrowException () {
        final UserRqDTO request = createUserRq();
        request.setEmail("wrongEmail");
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Email has an invalid format", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndPasswordIsInvalidThenThrowException () {
        final UserRqDTO request = createUserRq();
        request.setPassword("wrongPassword");
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Password has an invalid format", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndPasswordHasNoNumbersThenThrowException () {
        final UserRqDTO request = createUserRq();
        request.setPassword("passwordWithOneNumber");
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Password has an invalid format", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndPasswordHasNoUpperCaseThenThrowException () {
        final UserRqDTO request = createUserRq();
        request.setPassword("password1234");
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Password has an invalid format", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndPasswordHasNoLowerCaseThenThrowException () {
        final UserRqDTO request = createUserRq();
        request.setPassword("PASSWORD1234");
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Password has an invalid format", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndPasswordHasOnlyOneNumberThenThrowException () {
        final UserRqDTO request = createUserRq();
        request.setPassword("Password1");
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Password has an invalid format", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndPasswordHasLessThanEightCharactersThenThrowException () {
        final UserRqDTO request = createUserRq();
        request.setPassword("aA1a1");
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Password has an invalid format", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndEmailExistsThenThrowException () {
        final UserRqDTO request = createUserRq();
        final Optional<User> user = Optional.of(createUser());
        when(this.userRepository.findByEmail(any())).thenReturn(user);
        try {
            this.userService.createUser(request);
        } catch (UserException e) {
            assertEquals("Email exists", e.getMessage());
        }
    }

    @Test
    public void whenCreateUserIsCalledAndDataIsValidThenReturnData () {
        final UserRqDTO request = createUserRq();
        final User user = createUser();
        when(this.userRepository.save(any(User.class))).thenReturn(user);

        final UserRsDTO result = this.userService.createUser(request);

        assertNotNull(result);
        assertEquals(request.getName(), request.getName());
        verify(this.userRqConverter).convert(request);
        verify(this.userRepository).save(any());
    }

    private UserRqDTO createUserRq() {
        List<PhonesRqDTO> phones = Arrays.asList(new PhonesRqDTO());
        return new UserRqDTO(NAME, EMAIL, PASSWORD, phones);
    }

    private User createUser() {
        return new User(NAME, EMAIL, PASSWORD, true);
    }

}
