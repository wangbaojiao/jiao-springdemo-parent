package com.rwbi.example.service;

import com.github.pagehelper.PageInfo;
import com.rwbi.example.model.User;

/**
 * @author ：wangbaojiao
 * @date ：Created in 6/18/2020 5:56 PM
 * @description：
 * @modified By：
 * @version: $
 */
public interface IUserService {

    PageInfo<User> getUserList(String searchName, int pageNum, int pageSize);

    public void removeCache();

}
