package com.wonders.service;

import com.wonders.entity.UserInfo;
import com.wonders.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author huangwieyue
 * @date 2018-02-13 11:52
 */
@Service
@CacheConfig(cacheNames = {"userInfo"})
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUser(Long uid) {
        return userInfoMapper.selectByPrimaryKey(uid);
    }

    /**
     * 查询user时候做的缓存
     *
     * @return
     */
    @Override
    @Cacheable(key = "#userInfo.uid")
    public UserInfo getUserBySpringCatche(UserInfo userInfo) {
        Long id = userInfo.getUid();
        UserInfo u1 = userInfoMapper.selectUserByCatche(id);
        System.out.println("查询结束为主键id为：" + id + "的数据做了缓存");


        return u1;
    }

    /**
     * 保存user的时候做缓存
     *
     * @param userInfo
     * @return
     */
    @Override
    @CachePut(key = "#userInfo.uid")
    public Integer updateUserBySpringCatche(UserInfo userInfo) {
        System.out.println("为id、key为:" + userInfo.getUid() + "的数据做了缓存");
        return userInfoMapper.updateByPrimaryKey(userInfo);
    }
}
