package org.example.authority.config.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-04
 */
public class CustomerAuthenticationException extends AuthenticationException {

    public CustomerAuthenticationException(String message){
        super(message);
    }
}
