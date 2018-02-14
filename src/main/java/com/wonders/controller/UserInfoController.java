package com.wonders.controller;

import com.wonders.entity.UserInfo;
import com.wonders.mapper.UserInfoMapper;
import com.wonders.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangwieyue
 * @date 2018-02-13 11:48
 * http://localhost:8080/springboot/getUser?uid=1
 */
@RestController
@RequestMapping("springboot")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 测试mybatis查询数据是否可以调通
     * @param uid
     * @return
     */
    @RequestMapping("/getUser")
    public UserInfo getUser(Long uid){
        return userInfoService.getUser(uid);
    }

    /**
     * 查询user的时候做的缓存
     * {
     "uid":2
     }
     * http://localhost:8080/springboot/getUserBySpringCatche
     * 测试方法：
     * 调用这个方法两次 缓存成功状态是第一次控制台打印sql查询语句 第二次不打印
     * @return
     */
    @RequestMapping(value = "/getUserBySpringCatche",method = RequestMethod.POST)
    public UserInfo getUserBySpringCatche(@RequestBody UserInfo userInfo){
        return userInfoService.getUserBySpringCatche(userInfo);
    }

    /**
     * 更新user的时候做的缓存 uid=2
     * 测试方法
     * http://localhost:8080/springboot/updateUserBySpringCatche
     * {
     "uid":2,
     "name":"name2",
     "state":1
     }
     */
    /*@RequestMapping(value = "/updateUserBySpringCatche",method = RequestMethod.POST)
    public Integer saveUserBySpringCatche(@RequestBody UserInfo userInfo){
        return userInfoService.updateUserBySpringCatche(userInfo);
    }*/


}
