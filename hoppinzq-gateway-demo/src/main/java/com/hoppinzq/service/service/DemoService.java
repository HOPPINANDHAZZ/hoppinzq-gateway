package com.hoppinzq.service.service;

import com.hoppinzq.service.aop.annotation.*;
import com.hoppinzq.service.bean.RequestContext;
import com.hoppinzq.service.bean.RequestParam;
import com.hoppinzq.service.bean.ServiceApiBean;
import com.hoppinzq.service.cache.apiCache;

import java.util.List;

/**
 * @author: zq
 */
@ApiServiceMapping(title = "测试服务", description = "测试服务",roleType = ApiServiceMapping.RoleType.RIGHT)
public class DemoService {

    static int ticket = 10;
    static int threadNum = 0;
    static int ticket2 = 10;
    static int threadNum2 = 0;

    /**
     * 测试接口
     * @return
     */
    @ApiMapping(value = "test", title = "测试接口", description = "测试接口")
    public String test(){
        //注册的接口列表，通过动态改变该列表的参数，实现对接口的定制和修改
        List<ServiceApiBean> serviceApiBeans= apiCache.outApiList;
        //本次请求的所有数据
        RequestParam requestParam=(RequestParam)RequestContext.getPrincipal();
        return "测试接口";
    }

    /**
     * 权限配置
     * @return
     */
    @ApiMapping(value = "testLogin", title = "测试接口-权限", description = "必须登录用户可调用",roleType = ApiMapping.RoleType.LOGIN)
    public String testLogin(){
        return "该接口需要登录用户可调用";
    }

    /**
     * 超时机制
     * @return
     */
    @Timeout(timeout = 3000)
    @ApiMapping(value = "testTimeout", title = "测试接口-超时机制", description = "接口响应时间超过3秒报错测试")
    public String testTimeout(){
        try{
            Thread.sleep(4000);
        }catch(Exception exception){

        }
        return "接口响应时间超过3秒报错测试";
    }

    /**
     * 请求类型
     * @return
     */
    @ApiMapping(value = "testPost", title = "测试接口-类型", description = "该接口只允许post请求",type=ApiMapping.Type.POST)
    public String testPost(){
        return "只允许post请求";
    }

    /**
     * 返回值封装
     * @return
     */
    @ApiMapping(value = "testReturn", title = "测试接口-返回值封装", description = "该接口返回值不会被包装",returnType=false)
    public String testReturn(){
        return "该接口返回值不会被包装";
    }

    /**
     * 返回值封装2
     * @return
     */
    @ReturnTypeUseDefault
    @ApiMapping(value = "testReturn2", title = "测试接口-返回值封装2", description = "该接口返回值不会被包装2")
    public String testReturn2(){
        return "该接口返回值不会被包装";
    }

    /**
     * 缓存机制
     * @return
     */
    @ApiCache
    @ApiMapping(value = "testCache", title = "测试接口-缓存", description = "该接口返回值会被缓存")
    public String testCache(){
        //注意，需要打开注释
        return "该接口返回值会被缓存，注意请打开ApiGatewayHand的注释，需要借助redis实现";
    }

    /**
     * 幂等机制
     * @return
     */
    @AutoIdempotent
    @ApiMapping(value = "testAutoIdempotent", title = "测试接口-幂等", description = "该接口幂等")
    public String testAutoIdempotent(){
        return "该接口幂等，注意请打开ApiGatewayHand类里的token方法的注释，需要借助redis实现";
    }


    /**
     * 限流机制
     * @return
     */
    @ServiceLimit(number = 1)
    @ApiMapping(value = "testLimit", title = "测试接口-限流", description = "该接口（同一ip）一秒只允许调用一次")
    public String testLimit(){
        return "该接口（同一ip）一秒只允许调用一次";
    }

    /**
     * 同步锁机制
     * @return
     * @throws Exception
     */
    @Servicelock
    @ApiMapping(value = "testLock", title = "测试接口-同步锁", description = "该接口被加入同步锁，共享数据只允许单线程操作")
    public String testLock() throws Exception{
        threadNum++;
        long l = new Double(10000*Math.random()).longValue();
        System.err.println("线程:"+threadNum+",进入，随机睡眠时间："+l+" ms");
        if(ticket<=0){
            return "线程:"+threadNum+"买票,没有票了";
        }else{
            Thread.sleep(l);//买票操作，用随机时间模拟买票0~10秒
            ticket--;
            return "线程:"+threadNum+"买票,剩余票数:"+ticket;
        }
    }

    //没有加同步锁，会有负数票数的情况
    @ApiMapping(value = "testLock2", title = "测试接口-同步锁2", description = "该接口没有被加入同步锁")
    public String testLock2() throws Exception{
        threadNum2++;
        long l = new Double(10000*Math.random()).longValue();
        System.err.println("线程:"+threadNum2+",进入，随机睡眠时间："+l+" ms");
        if(ticket2<=0){
            return "线程:"+threadNum2+"买票,没有票了";
        }else{
            Thread.sleep(l);//买票操作，用随机时间模拟买票0~10秒
            ticket2--;
            return "线程:"+threadNum2+"买票,剩余票数:"+ticket2;
        }
    }

    @ApiMapping(value = "testLog", title = "测试接口-日志", description = "该接口会连日志一起返回",log = true)
    public String testLog(){
        return "该接口是成功的，会连日志一起返回";
    }

    @ApiMapping(value = "testLog2", title = "测试接口-日志", description = "报错的日志返回值",log = true)
    public String testLog2(){
        Object empty=null;
        empty.toString();
        return "该接口是报错的，会连日志一起返回";
    }

}
