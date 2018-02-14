package com.wonders.service;

import com.wonders.entity.UserInfo;
import com.wonders.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author huangwieyue
 * @date 2018-02-13 11:52
 */
@Service
//@CacheConfig(cacheNames = "userInfo")
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
    @Cacheable(value = "userInfo",key = "#userInfo.uid")
    public UserInfo getUserBySpringCatche(UserInfo userInfo) {
        Long id = userInfo.getUid();
        UserInfo u1 = userInfoMapper.selectUserByCatche(id);
        System.out.println("查询结束为主键id为：" + id + "的数据做了缓存");


        return u1;
    }

    /**
     * 保存或者更新user的时候做缓存
     * !!!!!!!!!!!!是吧返回值存入value!!!!!!!!!!!!!!
     * xml中 flushCache="false"
     * @param userInfo
     * @return
     */
    @Override
    @CachePut(value = "userInfo",key = "#userInfo.uid")//这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中.即调用该方法时，会把uid作为key，返回值作为value放入缓存；
    public UserInfo saveOrUpdateUserBySpringCatche(UserInfo userInfo) {
        System.out.println("为id、key为:" + userInfo.getUid() + "的数据做了update缓存");
        userInfoMapper.updateByPrimaryKey(userInfo);
        //userInfoMapper.insert(userInfo);
        return userInfo;
    }


    /**
     * 删除
     */
    //@CacheEvict(cacheNames="books", allEntries=true)  @CacheEvict(value = "getName", key = "#user.id")
   /* @CacheEvict(value = "user", key = "#user.id") //移除指定key的数据
    public User delete(User user) {
        users.remove(user);
        return user;
    }
    @CacheEvict(value = "user", allEntries = true) //移除所有数据
    public void deleteAll() {
        users.clear();
    }*/
}
