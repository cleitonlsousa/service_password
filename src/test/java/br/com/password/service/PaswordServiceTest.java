package br.com.password.service;

import br.com.password.service.api.request.PasswordRequest;
import br.com.password.service.service.PasswordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class PaswordServiceTest {

    private PasswordService passwordService;

    @Before
    public void before(){

        passwordService = mock(PasswordService.class);
        Mockito.when(passwordService.isValid(Mockito.any())).thenCallRealMethod();

    }

    @Test
    public void valid(){
        PasswordRequest request = new PasswordRequest();
        request.setPassword("AbTp9!fok");

        Assert.assertTrue(passwordService.isValid(request));

    }

    @Test
    public void notValid(){
        PasswordRequest request = new PasswordRequest();
        request.setPassword("AbTp9 fok");

        Assert.assertFalse(passwordService.isValid(request));

    }

    @Test
    public void notValidNullRequest(){

        Assert.assertFalse(passwordService.isValid(null));

    }

    @Test
    public void notValidNullPasswordRequest() throws Exception {

        Assert.assertFalse(passwordService.isValid(new PasswordRequest()));

    }

    @Test
    public void notValidEmptyPasswordRequest() throws Exception {

        PasswordRequest request = new PasswordRequest();
        request.setPassword("");

        Assert.assertFalse(passwordService.isValid(request));

    }

    @Test
    public void notValidSpacePasswordRequest() throws Exception {

        PasswordRequest request = new PasswordRequest();
        request.setPassword(" ");

        Assert.assertFalse(passwordService.isValid(request));

    }


    @Test
    public void notValidCharDuplicated(){
        PasswordRequest request = new PasswordRequest();
        request.setPassword("AbTp9!foA");

        Assert.assertFalse(passwordService.isValid(request));
    }


}
