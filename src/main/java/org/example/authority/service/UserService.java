package org.example.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.authority.entity.User;


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

}
