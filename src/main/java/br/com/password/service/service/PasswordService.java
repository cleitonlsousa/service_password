package br.com.password.service.service;

import br.com.password.service.api.request.PasswordRequest;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private static final String REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+])(?=\\S+$).{9,}";

    public boolean isValid(PasswordRequest request){

        if (request == null || request.getPassword() == null || request.getPassword().trim().isEmpty())
            return Boolean.FALSE;

        return request.getPassword().matches(REGEX);

    }


}
