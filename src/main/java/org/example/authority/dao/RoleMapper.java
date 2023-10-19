package org.example.authority.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.authority.entity.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author example
 * @since 2022-11-03
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select count(1) from sys_user_role where role_id = #{roleId}")
    int getRoleCountByRoleId(Long roleId);

    @Delete("delete from sys_role_permission where role_id = #{roleId}")
    void deleteRolePermissionByRoleId(Long roleId);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    int saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 根据用户ID查询该用户拥有的角色ID
     * @param userId
     * @return
     */
    @Select("select role_id from `sys_user_role` where user_id = #{userId}")
    List<Long> findRoleIdByUserId(Long userId);
}
