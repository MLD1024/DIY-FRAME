package lz.com.http.spring.boot.executor;


import lz.com.http.spring.boot.binding.HttpType;

import java.io.IOException;
import java.util.Map;

/**
 * 〈执行器〉
 *
 * @author LZ
 * @create 2019/11/19
 * @since 1.0.0
 */
public class HttpExecutor {


    public static String execute(Map<String, Object> paramMap, String url, HttpType type) {
        switch (type) {
            case GET:
                try {
                    return new GetMethod().request(url, paramMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case POST:
                try {
                    return new PostMethod().request(url, paramMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            default:
                throw new RuntimeException("该类型" + type + "暂不支持");
        }
    }
}
