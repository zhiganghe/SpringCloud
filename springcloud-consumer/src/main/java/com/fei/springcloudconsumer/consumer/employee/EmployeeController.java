package com.fei.springcloudconsumer.consumer.employee;

import com.fei.common.model.employee.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/6/21
 * @Description: 员工消费端（控制器）
 */
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    /**
     * 定义url常量
     */
    private static final String REST_URL = "http://127.0.0.1:8081";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取员工列表，调用服务
     * 1. 通过RestTemplate调用服务，get请求参数列表：url、返回值类型（参数列表写在url中）
     * 2. 通过RestTemplate调用服务，post请求参数列表：url、参数列表、返回值类型
     */
    @GetMapping("/list")
    public List<EmployeeModel> list() {
        return restTemplate.getForObject(REST_URL + "/emp/list", List.class);
    }

}