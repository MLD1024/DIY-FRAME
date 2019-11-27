package com.lz.dao;

import com.lz.bean.UserInfo;
import lz.com.http.spring.boot.annotations.MethodType;
import lz.com.http.spring.boot.binding.HttpType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈hello dao〉
 *
 * @author LZ
 * @create 2019/11/21
 * @since 1.0.0
 */
@Repository
public interface HelloDao {

    @MethodType(method = HttpType.GET)
    List<UserInfo> queryByAreaDistrict(String district, String area);
}
