package com.slideviewsoft.pt.dao.impl;

import org.springframework.stereotype.Repository;

import com.slideviewsoft.pt.dao.IProductInterfaceDao;
import com.slideviewsoft.pt.util.PageInfo;
import com.slideviewsoft.pt.vo.ProductInterface;

@Repository("productInterfaceDao")
public class ProductInterfaceDao extends PageBaseDAO implements IProductInterfaceDao{
	
	@Override
	public PageInfo getPageList(String hql, int pageNo, int pageCount) {
		if (hql == null) {
			hql = "from ProductInterface";
		}
		if (pageCount == 0) {
			pageCount = 1;
		}
		return this.getPage(hql, pageNo, pageCount);
	}

	@Override
	public ProductInterface loadProductInterface(String prodactName,
			String parameter) {
		ProductInterface p = (ProductInterface)this.getSession().createQuery("from ProductInterface where productname = ? and ifparameter = ?").setParameter(0, prodactName).setParameter(1, parameter).uniqueResult();
		return p;
	}
}