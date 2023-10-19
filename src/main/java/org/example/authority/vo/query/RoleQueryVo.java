package org.example.authority.vo.query;

import org.example.authority.entity.Role;
import lombok.Data;

/**
 * @author bingo
 * @description RoleQueryVo查询条件类
 * @date 2022-11-06
 */
@Data
public class RoleQueryVo extends Role {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
    private Long userId;//用户ID
}
