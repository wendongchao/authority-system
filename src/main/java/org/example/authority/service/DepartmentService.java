package org.example.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.authority.entity.Department;
import org.example.authority.vo.query.DepartmentQueryVo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author example
 * @since 2022-11-03
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 查询部门列表
     * @param departmentQueryVo
     * @return
     */
    List<Department> findDepartmentList(DepartmentQueryVo departmentQueryVo);

    /**
     * 查询上级部门列表
     * @return
     */
    List<Department> findParentDepartment();

    /**
     * 判断部门下是否有子部门
     * @param id
     * @return
     */
    boolean hasChildrenOfDepartment(Long id);

    /**
     * 判断部门下是否存在用户
     * @param id
     * @return
     */
    boolean hasUserOfDepartment(Long id);

}
