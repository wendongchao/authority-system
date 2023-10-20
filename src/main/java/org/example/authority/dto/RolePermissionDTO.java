package org.example.authority.dto;

import lombok.Data;

import java.util.List;

/**
 * @author bingo
 * @description 用于给角色分配权限时保存 选中的权限数据
 * @date 2022-11-07
 */
@Data
public class RolePermissionDTO {
    private Long roleId;//角色编号
    private List<Long> list;//权限菜单ID集合
}
