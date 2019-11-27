package com.lz.controller;

import com.lz.bean.UserInfo;
import com.lz.dao.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 〈hello controller〉
 *
 * @author LZ
 * @create 2019/11/21
 * @since 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private HelloDao helloDao;

    @GetMapping("/hello")
    public List<UserInfo> sayHello() {
        // service
        return helloDao.queryByAreaDistrict("嘉定区", "南翔");
    }
}
