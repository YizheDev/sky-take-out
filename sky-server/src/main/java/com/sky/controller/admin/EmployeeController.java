package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }


    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("员工退出")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping("/addEmployee")
    @ApiOperation("员工添加")
    public Result<String> addEmployee(@RequestBody EmployeeDTO employeeDTO) throws InvocationTargetException, IllegalAccessException {

        log.info("员工添加:{}",employeeDTO);
        employeeService.save(employeeDTO);

        return Result.success();
    }
    /**
     * 分页查询
     *
     * @return
     */

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){

        log.info("分页信息查询：{}", employeePageQueryDTO);
        PageResult pageResult =  employeeService.pageQuary(employeePageQueryDTO);

        return Result.success(pageResult);

    }


    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用")
    public Result StartOrStop(@PathVariable Integer status, long id){

        log.info("启用禁用员工账号：{},{}",status, id);
        employeeService.StartOrStop(status, id);

        return Result.success();

    }

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    @GetMapping
    @ApiOperation("查询员工信息")
    public Result<Employee> getById(long id){

        Employee employee = employeeService.getById(id);

        return Result.success(employee);
    }

    /**
     * 员工编辑
     * @param employeeDTO
     * @return
     */
    @SneakyThrows
    @PutMapping()
    @ApiOperation("员工编辑")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("员工编辑：{}", employeeDTO);

        employeeService.update(employeeDTO);
        return Result.success();
    }

}
