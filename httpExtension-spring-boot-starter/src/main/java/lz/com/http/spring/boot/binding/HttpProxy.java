package lz.com.http.spring.boot.binding;

import com.alibaba.fastjson.JSON;
import lz.com.http.spring.boot.annotations.MethodType;
import lz.com.http.spring.boot.config.HttpExtensionConfiguration;
import lz.com.http.spring.boot.config.HttpResultMap;
import lz.com.http.spring.boot.executor.HttpExecutor;
import lz.com.http.spring.boot.mapping.HttpMappedStatement;
import lz.com.http.spring.boot.reflection.Invoker;
import lz.com.http.spring.boot.reflection.MetaClass;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        // String apiName = method.getName();
        String apiName = annotation.name();
        if ("".equals(apiName)) {
            apiName = method.getName();
        }
        HttpMappedStatement httpMappedStatement = configuration.httpMappedStatementMap.get(apiName);
        String url = httpMappedStatement.getUrl();
        // step3 拼装map参数
        HashMap<String, Object> paramMap = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            paramMap.put(parameters[i].getName(), args[i].toString());
        }

        // step4 获取返回类型
        Class<?> returnType = method.getReturnType();

        // step5 执行请求
        String response = HttpExecutor.execute(paramMap, url, type);
        Map<String, String> resultMap = httpMappedStatement.getResultMap();
        HttpResultMap httpResultMap = new HttpResultMap();
        MetaClass metaClass = new MetaClass(HttpResultMap.class);
        Map<String, Invoker> setMethods = metaClass.getSetMethods();
        if (resultMap != null) {
            Map map = JSON.parseObject(response, Map.class);
            Set<String> resultPropertys = resultMap.keySet();
            for (String resultProperty : resultPropertys) {
                // value
                Object value = map.get(resultProperty);
                // property
                String beanProperty = resultMap.get(resultProperty);
                Invoker invoker = setMethods.get(beanProperty);
                invoker.invoke(httpResultMap, value);
            }
        } else {
            return JSON.parseObject(response, returnType);
        }
        // step6 处理返回结果
        String s = JSON.toJSONString(httpResultMap.getData());
        return JSON.parseObject(s, returnType);

    }
}
