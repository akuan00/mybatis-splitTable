package com.qinkuan.split.demo.service;

import com.qinkuan.split.demo.mapper.UserMapper;
import com.qinkuan.split.demo.model.User;
import com.qinkuan.split.mapper.IMapper;
import com.qinkuan.split.service.AbstractMapperService;
import com.qinkuan.split.type.SplitType;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends AbstractMapperService<User, Long> {
    @Autowired
    UserMapper userMapper;

    @Override
    protected SplitType getSplitType() {
        return SplitType.ID_HASH;
    }


    @Override
    public String cacheNamespace() {
        return "user:";
    }

    @Override
    protected IMapper getMapper() {
        return userMapper;
    }




    public void updateNickNameByUserId(Long userId, String nickname) {

        this.updateSelective(userId,getOneMap("nickname",nickname),getOneMap("id",userId));
    }

    public List<User> findByJoon(){
        return userMapper.findByJoin(getTableName(null));
    }


    @NotNull
    private Map<String, Object> getOneMap(String key,Object v) {
        Map<String,Object> map = new HashMap<>();
        map.put(key,v);
        return map;
    }



}
