package com.hoppinzq.service.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hoppinzq.service.aop.annotation.ApiMapping;
import com.hoppinzq.service.aop.annotation.ApiServiceMapping;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: zq
 */
@ApiServiceMapping(title = "测试服务", description = "测试服务",roleType = ApiServiceMapping.RoleType.RIGHT)
public class DemoService {

    @ApiMapping(value = "test", title = "测试接口", description = "测试接口",roleType = ApiMapping.RoleType.LOGIN)
    public String getCSDNBlogMessage(String url) throws NoSuchAlgorithmException, KeyManagementException {
        return url;
    }
}
