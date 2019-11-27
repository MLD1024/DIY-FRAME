package lz.com.http.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 〈HttpExtension 相关属性〉
 *
 * @author LZ
 * @create 2019/11/19
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = HttpExtensionProperties.HTTPEXTENSION_PREFIX)
public class HttpExtensionProperties {
    public static final String HTTPEXTENSION_PREFIX = "httpextension";
    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    private String name;
    private String[] urlMapperLocations;


    public Resource[] resolveMapperLocations() {
        return Stream.of(Optional.ofNullable(this.urlMapperLocations).orElse(new String[0]))
                .flatMap(location -> Stream.of(getResources(location))).toArray(Resource[]::new);
    }

    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException e) {
            return new Resource[0];
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getUrlMapperLocations() {
        return urlMapperLocations;
    }

    public void setUrlMapperLocations(String[] urlMapperLocations) {
        this.urlMapperLocations = urlMapperLocations;
    }
}
