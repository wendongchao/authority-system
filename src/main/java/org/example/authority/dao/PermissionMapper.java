package org.example.authority.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.authority.entity.Permission;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bingo
 * @since 2022-11-03
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户ID查询权限列表
     * @param userId
     * @return
     */
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 根据角色ID查询权限列表
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(Long roleId);
}
