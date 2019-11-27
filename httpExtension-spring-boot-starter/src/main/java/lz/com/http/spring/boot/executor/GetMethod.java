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
    @Override
    public String request(String url, Map<String, Object> paramsMap) throws IOException {
        String paramUrl = HttpUtil.getQueryString(url, paramsMap);
        Request request = new Request.Builder().url(paramUrl).build();
        return run(request);
    }
}
