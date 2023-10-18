package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;

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
}
