package org.example.authority.vo.query;

import org.example.authority.entity.User;
import lombok.Data;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-07
 */
@Data
public class UserQueryVo extends User {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
}
