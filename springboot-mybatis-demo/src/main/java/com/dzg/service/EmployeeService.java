package com.dzg.service;

import com.dzg.entity.Employee;
import com.dzg.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /*    public EmployeeService(EmployeeMapper employeeMapper) {
            this.employeeMapper = employeeMapper;
        }*/

    //增加员工
    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    //批量增加员工
    public int addEmployees(List<Employee> list) {
        return employeeMapper.addEmployees(list);
    }

    //动态增加用户记录
    public int insertSelective(Employee record) {
        return employeeMapper.insertSelective(record);
    }

    //根据主键删除员工
    public int deleteByPrimaryKey(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    //根据主键修改员工信息
    public int updateByPrimaryKey(Employee record) {
        return employeeMapper.updateByPrimaryKey(record);
    }

    //根据主键查找员工信息
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    //分页查询
    public List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size,
                                     @Param("emp") Employee employee) {
        return employeeMapper.getEmployeeByPage(page, size, employee);
    }

    //获取总的记录数(动态的模糊查询)
    public Long getTotalRecords(@Param("emp") Employee employee) {
        return employeeMapper.getTotalRecords(employee);
    }

    public int getMaxId(){
        return employeeMapper.getMaxId();
    }
}
