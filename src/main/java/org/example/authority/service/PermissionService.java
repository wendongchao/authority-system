package org.example.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.authority.entity.Permission;
import org.example.authority.vo.query.PermissionQueryVo;
import org.example.authority.vo.query.RolePermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bingo
 * @since 2022-11-03
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据用户ID查询权限列表
     * @param userId
     * @return
     */
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */
    List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo);

    /**
     * 查询上级菜单列表
     * @return
     */
    List<Permission> findParentPermissionList();

    /**
     * 检查菜单是否有子菜单
     * @param id
     * @return
     */
    boolean hasChildrenOfPermission(Long id);

    /**
     * 查询分配权限树列表
     * @param userId
     * @param roleId
     * @return
     */
    RolePermissionVo findPermissionTree(Long userId, Long roleId);


}
