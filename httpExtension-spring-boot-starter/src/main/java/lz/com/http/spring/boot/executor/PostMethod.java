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

    private String url;
    private Object param;

    public PostMethod(String url, Object param) {
        this.url = url;
        this.param = param;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    @Override
    public String execute() {
        RequestBody requestBody = RequestBody.create(MEDIATYPE, JSON.toJSONString(param));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            return run(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
