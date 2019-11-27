package lz.com.http.spring.boot.binding;
import lz.com.http.spring.boot.config.HttpExtensionConfiguration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**o
 * 〈http 映射类〉
 *
 * @author LZ
 * @create 2019/11/18
 * @since 1.0.0
 */
public class HttpMapperFactoryBean<T> implements FactoryBean<T> , InitializingBean {

    private HttpExtensionConfiguration httpExtensionConfiguration;
    private Class<T> httpMapperInterface;
    public HttpMapperFactoryBean(Class<T> httpMapperInterface) {
        this.httpMapperInterface = httpMapperInterface;
    }

    public HttpMapperFactoryBean() {

    }

    @Override
    public T getObject() throws Exception {
        HttpProxyFactory<T> httpProxyFactory = new HttpProxyFactory<>(httpMapperInterface,httpExtensionConfiguration);
        return httpProxyFactory.newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return this.httpMapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
    public HttpExtensionConfiguration getHttpExtensionConfiguration() {
        return httpExtensionConfiguration;
    }

    public void setHttpExtensionConfiguration(HttpExtensionConfiguration httpExtensionConfiguration) {
        this.httpExtensionConfiguration = httpExtensionConfiguration;
    }
}
