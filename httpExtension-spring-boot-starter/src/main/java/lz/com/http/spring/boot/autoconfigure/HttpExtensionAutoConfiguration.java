package lz.com.http.spring.boot.autoconfigure;


import lz.com.http.spring.boot.builder.xml.XMLConfigBuilder;
import lz.com.http.spring.boot.config.HttpExtensionConfiguration;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 〈httpExtension 自动注入类〉
 *
 * @author LZ
 * @create 2019/11/19
 * @since 1.0.0
 */
@Configuration//开启配置
@EnableConfigurationProperties(HttpExtensionProperties.class)//开启使用映射实体对象
public class HttpExtensionAutoConfiguration implements InitializingBean {
    private final HttpExtensionProperties properties;
    private final ResourceLoader resourceLoader;

    public HttpExtensionAutoConfiguration(HttpExtensionProperties properties, ResourceLoader resourceLoader) {
        this.properties = properties;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void afterPropertiesSet() {
        checkConfigFileExists();
    }

    private void checkConfigFileExists() {
        Resource[] resources = properties.resolveMapperLocations();
    }

    @Bean
    public HttpExtensionConfiguration generate() {
        Resource[] resources = properties.resolveMapperLocations();
        InputStream inputStream = null;
        try {
            inputStream = resources[0].getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpExtensionConfiguration httpExtensionConfiguration = new HttpExtensionConfiguration();
        try {
            XMLConfigBuilder.parse(inputStream, httpExtensionConfiguration);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return httpExtensionConfiguration;
    }
}
