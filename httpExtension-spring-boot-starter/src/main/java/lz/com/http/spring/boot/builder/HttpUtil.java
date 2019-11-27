package lz.com.http.spring.boot.builder;

import java.util.Map;
import java.util.Set;

/**
 * 〈http 工具类〉
 *
 * @author LZ
 * @create 2019/11/18
 * @since 1.0.0
 */
public class HttpUtil {

    /**
     * @param url      路径url
     * @param paramMap 参数列表
     * @return StringBuffer 拼接好的get请求url
     */
    public static String getQueryString(String url, Map<String,Object> paramMap) {
        StringBuffer sb = new StringBuffer(url);
        if (paramMap != null && paramMap.size() > 0) {
            Set<Map.Entry<String, Object>> entries = paramMap.entrySet();
            boolean firstFlag = true;
            for (Map.Entry<String, Object> entry : entries) {
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue().toString());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue().toString());
                }
            }
        }
        return sb.toString();
    }

}
