package org.example.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.authority.entity.User;
import org.example.authority.vo.query.UserQueryVo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bingo
 * @since 2022-11-03
 */
public interface UserService extends IService<User> {

    User findUserByUserName(String username);

    /**
     * 分页查询用户信息
     * @param page
     * @param userQueryVo
     * @return
     */
    IPage<User> findUserListByPage(IPage<User> page, UserQueryVo userQueryVo);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 分配角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveUserRole(Long userId, List<Long> roleIds);

}
