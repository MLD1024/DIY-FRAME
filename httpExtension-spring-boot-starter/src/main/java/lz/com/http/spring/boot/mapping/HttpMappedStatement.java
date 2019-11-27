package lz.com.http.spring.boot.mapping;

import java.util.Map;

/**
 * 〈http mapper 映射封装类〉
 *
 * @author LZ
 * @create 2019/11/26
 * @since 1.0.0
 */
public class HttpMappedStatement {


    private String url;
    private Map<String, String> resultMap;
    private String type;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
