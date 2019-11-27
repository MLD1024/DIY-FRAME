package lz.com.http.spring.boot.reflection;


import java.lang.reflect.Method;
import java.util.*;

/**
 * 〈class 操作类〉
 *
 * @author LZ
 * @create 2019/11/27
 * @since 1.0.0
 */
public class MetaClass {
    private final Class<?> type;
    private final Map<String, Invoker> setMethods = new HashMap<String, Invoker>();
    private final Map<String, Invoker> getMethods = new HashMap<String, Invoker>();

    public MetaClass(Class<?> clazz) {
        this.type = clazz;
        resolveMethods();
    }

    private void resolveMethods() {
        // 获取所有的方法
        Method[] methods = type.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("set") && name.length() > 3) {
                if (method.getParameterTypes().length == 1) {
                    name = PropertyNamer.methodToProperty(name);
                    setMethods.put(name, new MethodInvoker(method));
                }
            }
            if (name.startsWith("get") && name.length() > 3) {
                if (method.getParameterTypes().length == 0) {
                    name = PropertyNamer.methodToProperty(name);
                    getMethods.put(name, new MethodInvoker(method));
                }
            }
        }
    }

    public Class<?> getType() {
        return type;
    }

    public Map<String, Invoker> getSetMethods() {
        return setMethods;
    }

    public Map<String, Invoker> getGetMethods() {
        return getMethods;
    }
}
