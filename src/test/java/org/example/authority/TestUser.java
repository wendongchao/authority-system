package org.example.authority;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-03
 */
@SpringBootTest
public class TestUser {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void TestPwd(){
        System.out.println(passwordEncoder.encode("123456"));
    }
}
