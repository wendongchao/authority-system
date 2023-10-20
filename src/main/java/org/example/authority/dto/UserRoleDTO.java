package org.example.authority.dto;

import lombok.Data;

import java.util.List;

/**
 * @author bingo
 * @description 用于给用户分配角色时保存选中的角色数据
 * @date 2022-11-09
 */
@Data
public class UserRoleDTO {
    private Long userId;
    private List<Long> roleIds;
}
