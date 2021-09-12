package com.dzg.mapper;

import com.dzg.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created By 邓治国
 * 2020/5/13
 */
public interface EmployeeMapper {

    //增加员工
    int addEmployee(Employee record);

    //批量增加员工
    int addEmployees(@Param("list") List<Employee> list);

    //动态增加用户记录
    int insertSelective(Employee record);

    //根据主键删除员工
    int deleteByPrimaryKey(Integer id);

    //根据主键动态修改员工信息
//    int updateByPrimaryKeySelective(Employee record);

    //根据主键修改员工信息
    int updateByPrimaryKey(Employee record);

    //根据主键查找员工信息
    Employee getEmployeeById(Integer id);

    //分页查询
    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size,
                                     @Param("emp") Employee employee);

    //获取总的记录数
    Long getTotalRecords(@Param("emp") Employee employee);

    int getMaxId();
}
