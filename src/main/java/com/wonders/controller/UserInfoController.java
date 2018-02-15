package com.wonders.controller;

import com.wonders.entity.UserInfo;
import com.wonders.mapper.UserInfoMapper;
import com.wonders.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangwieyue
 * @date 2018-02-13 11:48
 * http://localhost:8080/springboot/getUser?uid=1
 *
 * 开涛大神：http://jinnianshilongnian.iteye.com/blog/2001040/
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
     *
     * @param uid
     * @return
     */
    @RequestMapping("/getUser")
    public UserInfo getUser(Long uid) {
        return userInfoService.getUser(uid);
    }

    /**
     * 查询user的时候做的缓存
     * {
     * "uid":2
     * }
     * http://localhost:8080/springboot/getUserBySpringCatche
     * 测试方法：
     * 调用这个方法2次 缓存成功状态是第一次控制台打印sql查询语句 第二次不打印
     *
     * @return
     */
    @RequestMapping(value = "/getUserBySpringCatche", method = RequestMethod.POST)
    public UserInfo getUserBySpringCatche(@RequestBody UserInfo userInfo) {
        return userInfoService.getUserBySpringCatche(userInfo);
    }

    /**
     * 更新user的时候做的缓存 uid=3
     * 测试方法
     * http://localhost:8080/springboot/saveOrUpdateUserBySpringCatche
     * {
     "uid":3,
     "name":"333",
     "state":1
     }
     注意更新（或者save）返回的数据是这条对象记录 不是主键id，把这个对象加入缓存才有意义。



     然后再根据主键查询
     http://localhost:8080/springboot/getUserBySpringCatche
     {
     "uid":3
     }
     控制台无打印表明走了缓存
     */
    @RequestMapping(value = "/saveOrUpdateUserBySpringCatche", method = RequestMethod.POST)
    public UserInfo saveOrUpdateUserBySpringCatche(@RequestBody UserInfo userInfo) {
        UserInfo user = userInfoService.saveOrUpdateUserBySpringCatche(userInfo);
        return user;
    }


    /**
     *http://localhost:8080/springboot/deleteUserBySpringCatche?uid=4
     * 先查询uid为4的两次 发现第一次走了后台第二次走了缓存
     * 这时候再调用deleteUserBySpringCatche()方法清楚缓存，再查询，发现走了后台说明缓存被清空了，要注意的是并不需要删除这条数据
     * 只要配置了@CacheEvict(value = "userInfo", key = "#uid") key相同就删除缓存了
     */
    @RequestMapping(value = "/deleteUserBySpringCatche", method = RequestMethod.GET)
    public UserInfo deleteUserBySpringCatche(@RequestParam("uid") Long uid) {
        UserInfo userInfo=userInfoMapper.selectByPrimaryKey(uid);
        userInfoService.evictUser(uid);
        return  userInfo;
    }

}
