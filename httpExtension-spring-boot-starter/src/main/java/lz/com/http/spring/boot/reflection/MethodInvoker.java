package lz.com.http.spring.boot.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 〈〉
 *
 * @author LZ
 * @create 2019/11/27
 * @since 1.0.0
 */
public class MethodInvoker implements Invoker {

    private final Class<?> type;
    private final Method method;

    public MethodInvoker(Method method) {
        this.method = method;
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        } else {
            type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object... args) throws IllegalAccessException, InvocationTargetException {
            return method.invoke(target, args);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}