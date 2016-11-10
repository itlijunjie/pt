package com.itlijunjie.pt.dao.impl;

import org.springframework.stereotype.Repository;

import com.itlijunjie.pt.dao.IUserDao;
import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.vo.User;

@Repository("userDao")
public class UserDao extends PageBaseDAO implements IUserDao {
	@Override
	public PageInfo getPageList(String hql, int pageNo, int pageCount) {
		if (hql == null) {
			hql = "from User";
		}
		if (pageCount == 0) {
			pageCount = 1;
		}
		return this.getPage(hql, pageNo, pageCount);
	}
	
	@Override
	public User loadByUsername(String username){
		User u = (User)this.getSession().createQuery("from User where username = ?").setParameter(0, username).uniqueResult();
		return u;
	}
}
