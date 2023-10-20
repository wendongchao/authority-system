package org.example.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.authority.dao.UserMapper;
import org.example.authority.entity.User;
import org.example.authority.service.FileService;
import org.example.authority.service.UserService;
import org.example.authority.utils.SystemConstants;
import org.example.authority.vo.query.UserQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author example
 * @since 2022-11-03
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private FileService fileService;

    @Override
    public User findUserByUserName(String username) {
        // 创建条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param userQueryVo
     * @return
     */
    @Override
    public IPage<User> findUserListByPage(IPage<User> page, UserQueryVo userQueryVo) {
        //创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //部门编号
        queryWrapper.eq(!ObjectUtils.isEmpty(userQueryVo.getDepartmentId()),"department_id",userQueryVo.getDepartmentId());
        //用户名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getUsername()),"username",userQueryVo.getUsername());
        //真实姓名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getRealName()),"real_name",userQueryVo.getRealName());
        //电话
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getPhone()),"phone",userQueryVo.getPhone());
        //查询并返回数据
        return baseMapper.selectPage(page,queryWrapper);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        //查询
        User user = baseMapper.selectById(id);
        //删除用户角色关系
        baseMapper.deleteUserRole(id);
        //删除用户
        if (baseMapper.deleteById(id) > 0) {
            //判断用户是否存在
            if (user != null && !ObjectUtils.isEmpty(user.getAvatar())
                    && !user.getAvatar().equals(SystemConstants.DEFAULT_AVATAR)) {
                //删除文件
                fileService.deleteFile(user.getAvatar());
            }
            return true;
        }
        return false;
    }

    /**
     * 分配角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public boolean saveUserRole(Long userId, List<Long> roleIds) {
        //删除该用户对应的角色信息
        baseMapper.deleteUserRole(userId);
        //保存用户角色信息
        return baseMapper.saveUserRole(userId,roleIds)>0;
    }

}
