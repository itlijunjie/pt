package com.itlijunjie.pt.dao;

import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.vo.User;

public interface IUserDao extends IPageBaseDAO {
	/**
	 * 列表所有的用户
	 * @return
	 */
	public PageInfo getPageList(String hql, int pageNo, int pageCount);
	/**
	 * 根据用户名获取用户对象
	 * @param username 用户名
	 * @return 返回用户对象
	 */
	public abstract User loadByUsername(String username);
}
