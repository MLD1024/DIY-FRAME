package lz.com.http.spring.boot.reflection;

import java.lang.reflect.InvocationTargetException;

/**
 * 〈执行方法〉
 *
 * @author LZ
 * @create 2019/11/27
 * @since 1.0.0
 */
public interface Invoker {
    Object invoke(Object target, Object...args) throws IllegalAccessException, InvocationTargetException;

    Class<?> getType();
}
