package com.dzg.controller;

import com.dzg.entity.Employee;
import com.dzg.entity.Nation;
import com.dzg.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    //测试用的数据
    Employee emp = new Employee();
    List<Employee> testList = new ArrayList<>();

    @GetMapping("/addEmployee")
    public String addEmployee(Employee employee) {
        dataSetForAddOne();
        int res = employeeService.addEmployee(emp);
        return res > 0 ? "添加成功" : "添加失败";
    }

    //...
    @GetMapping("/addEmployees")
    public String addEmployees(@RequestParam List<Employee> list) {
        dataSetForAddMulti();
        int res = employeeService.addEmployees(testList);
        return res > 0 ? "添加成功" : "添加失败";
    }

    @GetMapping("/addSelective")
    public String insertSelective(Employee record) {
        Employee emp2 = new Employee();
        emp2.setName("添加员工" + (employeeService.getMaxId() + 1));
        emp2.setWorkAge(2);
        emp2.setNation(new Nation(100, "汉族"));
        int res = employeeService.insertSelective(emp2);
        return res > 0 ? "添加成功" : "添加失败";
    }


    @DeleteMapping("/deleteById/{id}")
    public String deleteByPrimaryKey(@PathVariable("id") Integer id) {
        int res = employeeService.deleteByPrimaryKey(id);
        return res > 0 ? "删除id号为：" + id + "的员工" : "删除失败";
    }

    @PutMapping("/update/{id}")
    public String updateByPrimaryKey(@PathVariable("id") int id, Employee record) {
        record.setId(id);
        record.setName("修改的员工");
        return employeeService.updateByPrimaryKey(record) > 0 ? "修改成功" : "修改失败";
    }


    @GetMapping("/getById/{id}")
    public String getEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return employee.toString();
        }
        return "查询失败";
    }

    //分页查询
    @GetMapping("getByPage/{page}/{size}")
    public String getEmployeeByPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                    Employee employee) {
        emp.setName("员工");
        List<Employee> employeeByPage = employeeService.getEmployeeByPage(page, size, emp);
        return Optional.ofNullable(employeeByPage).toString();
    }

    private void dataSetForAddOne() {
        int id = employeeService.getMaxId() + 1;
        emp.setId(id);
        emp.setName("添加员工" + id);
        emp.setGender("女");
        emp.setIdCard("11223344");
        emp.setNationId(12);
        emp.setNativePlace("江苏");
        emp.setPhone("15895881426");
        emp.setBeginContract(LocalDate.now().toString());
        emp.setEndContract("2035-12-30");
        emp.setWorkAge(2);
    }

    private void dataSetForAddMulti() {

        Employee emp1 = new Employee();
        emp1.setName("添加员工1");
        emp1.setWorkAge(2);
        Employee emp2 = new Employee();
        emp2.setName("添加员工2");
        emp2.setWorkAge(2);
        testList.add(emp1);
        testList.add(emp2);
    }
}
