package lz.com.http.spring.boot.config;

import lz.com.http.spring.boot.mapping.HttpMappedStatement;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 〈http extension 配置文件〉
 *
 * @author LZ
 * @create 2019/11/21
 * @since 1.0.0
 */
public class HttpExtensionConfiguration {
    public Map<String, Map<String, String>> resulteMap = new HashMap<>();
    public Map<String, HttpMappedStatement> httpMappedStatementMap = new HashMap<>();

}
