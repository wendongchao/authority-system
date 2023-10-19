package org.example.authority.vo.query;

import org.example.authority.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingo
 * @description 角色权限菜单数据回显Vo类
 * @date 2022-11-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionVo {
    /**
     * 菜单数据
     */
    private List<Permission> permissionList = new ArrayList<Permission>();

    /**
     * 该角色原有分配的菜单数据
     */
    private Object [] checkList;
}
