package lz.com.http.spring.boot.binding;

import com.alibaba.fastjson.JSON;
import lz.com.http.spring.boot.annotations.MethodType;
import lz.com.http.spring.boot.config.HttpExtensionConfiguration;
import lz.com.http.spring.boot.executor.DefaultResultHandler;
import lz.com.http.spring.boot.executor.GetMethod;
import lz.com.http.spring.boot.executor.HttpMethod;
import lz.com.http.spring.boot.executor.PostMethod;
import lz.com.http.spring.boot.mapping.HttpMappedStatement;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 〈http 执行代理〉
 *
 * @author LZ
 * @create 2019/11/18
 * @since 1.0.0
 */
public class HttpProxy implements InvocationHandler {

    private HttpExtensionConfiguration configuration;

    public HttpProxy(HttpExtensionConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // step1 获取http 请求方式
        MethodType annotation = method.getAnnotation(MethodType.class);
        HttpType type = annotation.method();


        // step2 获取方法的名字
        String apiName = annotation.name();
        if ("".equals(apiName)) {
            apiName = method.getName();
        }
        HttpMappedStatement httpMappedStatement = configuration.httpMappedStatementMap.get(apiName);
        String url = httpMappedStatement.getUrl();


        // step3 封装httpMethod 对象
        HttpMethod httpMethod = null;
        switch (type) {
            case GET:
                // step3 拼装map参数
                HashMap<String, Object> paramMap = new HashMap<>();
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    paramMap.put(parameters[i].getName(), args[i].toString());
                }
                httpMethod = new GetMethod(url, paramMap);
                break;
            case POST:
                // step3 拼装map参数
                String paramJson = JSON.toJSONString(args[0]);
                httpMethod = new PostMethod(url, paramJson);
                break;
            default:
                throw new RuntimeException("不存在" + type + "方法");
        }

        // step4 获取返回类型
        Type returnType = method.getGenericReturnType();
        // step5 执行请求
        String response = httpMethod.execute();
        return  DefaultResultHandler.handler(response,httpMappedStatement,returnType);




    }
}
