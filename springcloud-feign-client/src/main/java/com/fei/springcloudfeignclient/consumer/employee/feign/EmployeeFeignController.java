package com.fei.springcloudfeignclient.consumer.employee.feign;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fei.springcloudfeignclient.date.model.WebEmployeeModel;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fei.common.data.ApiResult;
import com.fei.common.enums.SexEnum;
import com.fei.common.model.employee.EmployeeModel;
import feign.webservice.EmployeeWebService;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/7/4
 * @Description: feign组件，消费服务接口
 */
@RestController
@RequestMapping("/consume/feign/empl")
public class EmployeeFeignController {

    /**
     * 使用了Feign，就像调用本地方法一样，调用远程HTTP请求
     */
    @Autowired
    private EmployeeWebService employeeWebService;

    /**
     * 获取员工列表，使用feign组件
     */
    @GetMapping("/list")
    public ApiResult<List<WebEmployeeModel>> list2() {
        List<EmployeeModel> employeeModels = employeeWebService.list();
        List<WebEmployeeModel> webEmployeeModels = new ArrayList<>();
        // 判空
        if (CollectionUtils.isNotEmpty(employeeModels)) {
            employeeModels.forEach(employeeModel -> {
                WebEmployeeModel webEmployeeModel = new WebEmployeeModel();
                // 使用Spring的工具进行属性拷贝
                BeanUtils.copyProperties(employeeModel, webEmployeeModel, WebEmployeeModel.class);
                // 性别描述
                webEmployeeModel.setEmployeeSexDesc(
                        Optional.ofNullable(SexEnum.getByCode(webEmployeeModel.getEmployeeSex()))
                                .map(SexEnum::getSexDesc).orElse("未知"));
                webEmployeeModels.add(webEmployeeModel);
            });
        }
        return ApiResult.ok(webEmployeeModels);
    }
}
