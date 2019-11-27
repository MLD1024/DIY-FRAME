package lz.com.http.spring.boot.executor;


import lz.com.http.spring.boot.config.OkHttpConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * 〈http方法〉
 *
 * @author LZ
 * @create 2019/11/18
 * @since 1.0.0
 */
@FunctionalInterface
public interface HttpMethod {
    OkHttpClient okHttpClient = OkHttpConfiguration.okHttpClient();

    String request(String url, Map<String, Object> paramsMap) throws IOException;

    default String run(Request request) throws IOException {
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
