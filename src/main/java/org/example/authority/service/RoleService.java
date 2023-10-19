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

}
