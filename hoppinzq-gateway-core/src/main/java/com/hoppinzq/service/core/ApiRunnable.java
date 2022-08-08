package com.hoppinzq.service.core;

import com.hoppinzq.service.bean.ServiceMethodApiBean;

import java.lang.reflect.Method;

/**
 * 网关找到服务并执行之
 * @author:ZhangQi
 **/
public class ApiRunnable {
    String apiName;
    //bean 名称
    String targetName;
    //实例
    Object target;
    //目标方法
    Method targetMethod;

    ServiceMethodApiBean serviceMethodApiBean;

    /**
     * 多线程安全问题
     * @param args
     * @return
     */
    public Object run(Object... args) throws Exception {
        if (target == null) {
            target=ApiStore.getApplicationContext().getBean(targetName);
        }
        return targetMethod.invoke(target, args);
    }

    public Class<?>[] getParamTypes() {
        return targetMethod.getParameterTypes();
    }

    public String getApiName() {
        return apiName;
    }

    public String getTargetName() {
        return targetName;
    }

    public Object getTarget() {
        return target;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public ServiceMethodApiBean getServiceMethodApiBean() {
        return serviceMethodApiBean;
    }
}
