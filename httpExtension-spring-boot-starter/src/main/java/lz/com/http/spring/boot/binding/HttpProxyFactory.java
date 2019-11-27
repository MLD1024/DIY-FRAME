package lz.com.http.spring.boot.binding;

import lz.com.http.spring.boot.config.HttpExtensionConfiguration;

import java.lang.reflect.Proxy;

/**
 * 〈HttpProxy 工厂类〉
 *
 * @author LZ
 * @create 2019/11/18
 * @since 1.0.0
 */
public class HttpProxyFactory<T> {

    private final Class<T> target;

    private HttpExtensionConfiguration configuration;

    public HttpProxyFactory(Class<T> target, HttpExtensionConfiguration configuration) {
        this.target = target;
        this.configuration = configuration;
    }

    public T newInstance() {
        HttpProxy httpProxy = new HttpProxy(configuration);
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, httpProxy);
    }

}
