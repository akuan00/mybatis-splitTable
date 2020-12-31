package com.qinkuan.split.demo.service;

import com.qinkuan.split.demo.mapper.UserMapper;
import com.qinkuan.split.demo.model.User;
import com.qinkuan.split.mapper.IMapper;
import com.qinkuan.split.service.AbstractMapperService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends AbstractMapperService<User, Long> {
    @Autowired
    UserMapper userMapper;


    @Override
    public String cacheNamespace() {
        return "user:";
    }

    @Override
    protected IMapper getMapper() {
        return userMapper;
    }



    public void get() {
        get(1L);
    }


    public void updateNickNameByUserId(Long userId, String nickname) {

        this.updateFieldById(getOneMap("nickname",nickname),userId);
    }

    @NotNull
    private Map<String, Object> getOneMap(String key,Object v) {
        Map<String,Object> map = new HashMap<>();
        map.put(key,v);
        return map;
    }






}
