package lz.com.http.spring.boot.executor;


import com.alibaba.fastjson.JSON;
import lz.com.http.spring.boot.config.HttpResultMap;
import lz.com.http.spring.boot.mapping.HttpMappedStatement;
import lz.com.http.spring.boot.reflection.Invoker;
import lz.com.http.spring.boot.reflection.MetaClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * 〈默认 返回结果处理器〉
 *
 * @author LZ
 * @create 2019/11/29
 * @since 1.0.0
 */
public class DefaultResultHandler implements ResultHandler {


    public static Object handler(String response, HttpMappedStatement httpMappedStatement, Type returnType) throws InvocationTargetException, IllegalAccessException {
        // step1 获取自定义映射对象
        Map<String, String> resultMap = httpMappedStatement.getResultMap();

        // step2 如果映射对象为空
        if (resultMap != null) {
            HttpResultMap httpResultMap = new HttpResultMap();
            MetaClass metaClass = new MetaClass(HttpResultMap.class);
            Map<String, Invoker> setMethods = metaClass.getSetMethods();
            Map map = JSON.parseObject(response, Map.class);
            Set<String> resultPropertys = resultMap.keySet();
            for (String resultProperty : resultPropertys) {
                // 获取返回结果的属性值
                Object value = map.get(resultProperty);
                // 获取与返回结果属性对应容器属性
                String beanProperty = resultMap.get(resultProperty);
                // 利用反射给容器赋值
                Invoker invoker = setMethods.get(beanProperty);
                invoker.invoke(httpResultMap, value);
            }
            // step6 处理返回结果
            String s = JSON.toJSONString(httpResultMap.getData());
            return JSON.parseObject(s, returnType);
        } else {
            return JSON.parseObject(response, returnType);
        }

    }
}
