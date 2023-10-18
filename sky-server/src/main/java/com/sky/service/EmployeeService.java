package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import java.lang.reflect.InvocationTargetException;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 员工添加
     * @param employeeLoginDTO
     * @return
     */
    void save(EmployeeDTO employeeLoginDTO) throws InvocationTargetException, IllegalAccessException;


    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuary(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用用户账号
     * @param status
     * @param id
     */
    void StartOrStop(Integer status, long id);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Employee getById(long id);

    /**
     * 员工编辑
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO) throws InvocationTargetException, IllegalAccessException;
}
