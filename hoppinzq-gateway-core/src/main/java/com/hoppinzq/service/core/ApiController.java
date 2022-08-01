package com.hoppinzq.service.core;

import com.alibaba.fastjson.JSONObject;
import com.hoppinzq.service.bean.ServiceApiBean;
import com.hoppinzq.service.cache.apiCache;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *
 */
@RestController
public class ApiController {

    @RequestMapping("/apiParams")
    public JSONObject getServiceMessage(){
        JSONObject jsonObject=new JSONObject();
        List<ServiceApiBean> serviceApiBeans= apiCache.outApiList;
        serviceApiBeans.get(0).getServiceMethods().get(0).setMethodReturn(false);
        jsonObject.put("api",serviceApiBeans);
        return jsonObject;
    }

}
