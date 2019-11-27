package lz.com.http.spring.boot.executor;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
 * 〈post 方法执行器〉
 *
 * @author LZ
 * @create 2019/11/19
 * @since 1.0.0
 */
public class PostMethod implements HttpMethod {
    private static final MediaType MEDIATYPE = MediaType.parse("application/json; charset=utf-8");

    @Override
    public String request(String url, Map<String, Object> paramsMap) throws IOException {
        RequestBody requestBody = RequestBody.create(MEDIATYPE, JSON.toJSONString(paramsMap));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return run(request);
    }
}
