package org.example.authority.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    //用户编号
    private Long id;
    //状态码
    private int code;
    //token令牌
    private String token;
    //token过期时间
    private Long expireTime;
}
