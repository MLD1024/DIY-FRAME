package lz.com.http.spring.boot.executor;

import lz.com.http.spring.boot.builder.HttpUtil;
import okhttp3.Request;

import java.io.IOException;
import java.util.Map;

/**
 * 〈get  方法执行器〉
 *
 * @author LZ
 * @create 2019/11/19
 * @since 1.0.0
 */
public class GetMethod implements HttpMethod {


    private String url;

    private Map<String, Object> paramsMap;

    public GetMethod(String url, Map<String, Object> paramsMap) {
        this.url = url;
        this.paramsMap = paramsMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }

    @Override
    public String execute() {
        String paramUrl = HttpUtil.getQueryString(url, paramsMap);
        Request request = new Request.Builder().url(paramUrl).build();
        try {
            return run(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
