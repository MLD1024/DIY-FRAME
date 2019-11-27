package lz.com.http.spring.boot.annotations;

import lz.com.http.spring.boot.binding.HttpType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LZ
 * @data 2019/11/19
 * @since 1.0.0
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodType {
    HttpType method();

    String name() default "";
}

