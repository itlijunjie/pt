package com.itlijunjie.pt.dao;

import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.vo.ProductInterface;

public interface IProductInterfaceDao extends IPageBaseDAO {
	public PageInfo getPageList(String hql, int pageNo, int pageCount);
	public abstract ProductInterface loadProductInterface(String prodactName , String parameter);
}