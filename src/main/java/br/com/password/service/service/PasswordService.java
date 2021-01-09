package br.com.password.service.service;

import br.com.password.service.api.request.PasswordRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordService {

    private static final String REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+])(?=\\S+$).{9,}";

    public boolean isValid(PasswordRequest request){

        if (request == null || request.getPassword() == null || request.getPassword().trim().isEmpty())
            return Boolean.FALSE;

        if(!validateDuplicateChar(request.getPassword()))
            return Boolean.FALSE;

        return request.getPassword().matches(REGEX);

    }

    private Boolean validateDuplicateChar(String password){

        List<Character> listChars = new ArrayList<>();

        for(char chars : password.toCharArray()){

            if(listChars.contains(chars)) {

                return Boolean.FALSE;

            }else {

                listChars.add(chars);
            }

        }

        return Boolean.TRUE;

    }


}
