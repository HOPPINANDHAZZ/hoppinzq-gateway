package com.hoppinzq.service.bean;

import com.hoppinzq.service.core.ApiRunnable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * @author:ZhangQi
 */
public class RequestParam implements Serializable{
    private static final long serialVersionUID = 1L;
    private String url;//本次请求的url
    private String cacheKey;//本次请求缓存的标志key
    private int cacheTime=0;//缓存时间
    private String params;//本次请求携带的参数列表
    private String method;//本次请求的目标方法
    private String sign;//本次请求携带的签名
    private String encode;//本次请求携带的加密字符串，也就是参数列表
    private String token;//本次请求携带的token
    private String timestamp;//本次请求携带的时间戳
    private Object result;//本次请求响应的数据
    private ApiRunnable apiRunnable;//本次请求调用方法的反射对象（包括名称，方法，实例，以及最重要的方法bean的解析参数）
    private List<FormInfo> formInfoList;//本次请求解析的表单数据（文件上传数据也在该参数内）
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestInfo log;//本次请求的日志（包括传参，响应时间，异常等等）
    private Object user;//本次请求的zauth认证的当前登录人（如果没有就是null）

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public RequestInfo getLog() {
        return log;
    }

    public void setLog(RequestInfo log) {
        this.log = log;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ApiRunnable getApiRunnable() {
        return apiRunnable;
    }

    public void setApiRunnable(ApiRunnable apiRunnable) {
        this.apiRunnable = apiRunnable;
    }

    public List<FormInfo> getFormInfoList() {
        return formInfoList;
    }

    public void setFormInfoList(List<FormInfo> formInfoList) {
        this.formInfoList = formInfoList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
