package lz.com.http.spring.boot.config;

/**
 * 〈返回结果 容器〉
 *
 * @author LZ
 * @create 2019/11/26
 * @since 1.0.0
 */
public class HttpResultMap {

    private Object status;
    private Object message;
    private Object data;

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
