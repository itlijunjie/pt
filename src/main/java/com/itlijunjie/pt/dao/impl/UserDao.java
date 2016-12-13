package com.itlijunjie.pt.dao.impl;

import com.itlijunjie.pt.dao.IUserDao;
import com.itlijunjie.pt.vo.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends PageBaseDAO implements IUserDao {

    @Override
    public User loadByUsername(String username) {
        User u = (User) this.getSession().createQuery("from User where username = ?").setParameter(0, username).uniqueResult();
        return u;
    }
}
