package org.example.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.authority.dao.RoleMapper;
import org.example.authority.dao.UserMapper;
import org.example.authority.entity.Role;
import org.example.authority.entity.User;
import org.example.authority.service.RoleService;
import org.example.authority.vo.query.RoleQueryVo;
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
 * @author bingo
 * @since 2022-11-03
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户查询角色列表
     *
     * @param page
     * @param roleQueryVo
     * @return
     */
    @Override
    public IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo) {
        //创建条件构造器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        //角色名称
        queryWrapper.like(!ObjectUtils.isEmpty(roleQueryVo.getRoleName()),"role_name",roleQueryVo.getRoleName());
        //排序
        queryWrapper.orderByAsc("id");
        //根据用户ID查询用户信息
        User user = userMapper.selectById(roleQueryVo.getUserId());
        //如果用户不为空、且不是管理员，则只能查询自己创建的角色
        if(user!=null && !ObjectUtils.isEmpty(user.getIsAdmin()) && user.getIsAdmin() !=1){
            queryWrapper.eq("create_user",roleQueryVo.getUserId());
        }
        return baseMapper.selectPage(page,queryWrapper);
    }

}
