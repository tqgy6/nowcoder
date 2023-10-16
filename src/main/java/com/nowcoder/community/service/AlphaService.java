package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public String selectData(){
        return alphaDao.select();
    }

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct  // 构造器之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy     // 销毁之前调用
    public void destroy(){
        System.out.println("销毁AlphaService");
    }
}
