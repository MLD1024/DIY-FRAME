package lz.com.http.spring.boot.annotations;

import lz.com.http.spring.boot.binding.HttpScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LZ
 * @create 2019/11/19
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(HttpScannerRegistrar.class)
public @interface HttpScan {


    /**
     * base 包
     */
    String[] basePackages() default {};

    /**
     * base 包
     */
    String[] value() default {};
}
