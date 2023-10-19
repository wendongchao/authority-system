package org.example.authority.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVo {
    //过期时间
    private Long expireTime;
    //token
    private String token;
}
