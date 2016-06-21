package com.slideviewsoft.pt.dao;

import com.slideviewsoft.pt.util.PageInfo;
import com.slideviewsoft.pt.vo.ProductInterface;

public interface IProductInterfaceDao extends IPageBaseDAO {
	public PageInfo getPageList(String hql, int pageNo, int pageCount);
	public abstract ProductInterface loadProductInterface(String prodactName , String parameter);
}