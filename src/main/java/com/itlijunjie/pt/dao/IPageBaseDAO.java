package com.itlijunjie.pt.dao;

import com.itlijunjie.pt.util.PageInfo;
@SuppressWarnings("rawtypes")
public interface IPageBaseDAO {
	public abstract Object add(Object object);
	public abstract void update(Object object);
	public abstract void delete(Class clazz,int id);
	public abstract Object load(Class clazz,int id);
	public int getCount(String hql);
	public PageInfo getPage(String hql, int pageNo, int pageCount);
	public PageInfo getPage(String hql, int pageNo);
}