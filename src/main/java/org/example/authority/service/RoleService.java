package org.example.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.authority.entity.Role;
import org.example.authority.vo.query.RoleQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bingo
 * @since 2022-11-03
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户查询角色列表
     * @param page
     * @param roleQueryVo
     * @return
     */
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo);

    /***
     * 检查该角色是否已被使用
     * @param id:
     * @return boolean
     */
    boolean hasRoleCount(Long id);

    /***
     * 删除角色
     * @param id:
     * @return boolean
     */
    boolean deleteRoleById(Long id);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 根据用户ID查询该用户拥有的角色ID
     * @param userId
     * @return
     */
    List<Long> findRoleIdByUserId(Long userId);

}
