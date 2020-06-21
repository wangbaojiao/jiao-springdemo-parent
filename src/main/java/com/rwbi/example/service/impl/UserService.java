package com.rwbi.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rwbi.example.mapper.UserMapper;
import com.rwbi.example.model.User;
import com.rwbi.example.model.UserExample;
import com.rwbi.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：wangbaojiao
 * @date ：Created in 6/18/2020 5:57 PM
 * @description：
 * @modified By：
 * @version: $
 */
@Service("userService")
//@CacheConfig(cacheNames = "userlist")
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable("userlist")
    public PageInfo<User> getUserList(String searchName, int pageNum, int pageSize) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!"".endsWith(searchName)){
            criteria.andNameLike("%"+searchName+"%");
        }
        PageHelper.startPage(0, 10);
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo page = new PageInfo(users);
        return page;
    }

    @Override
    @CacheEvict(cacheNames="userlist", allEntries=true)
    public void removeCache() {
    }

}
