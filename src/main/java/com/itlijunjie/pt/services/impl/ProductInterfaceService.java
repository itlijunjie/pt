package com.itlijunjie.pt.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itlijunjie.pt.dao.IProductInterfaceDao;
import com.itlijunjie.pt.services.IProductInterfaceService;
import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.vo.ProductInterface;
import com.itlijunjie.pt.vo.exception.ProductInterfaceException;

@Service("productInterfaceService")
public class ProductInterfaceService implements IProductInterfaceService {

	private IProductInterfaceDao productInterfaceDao;
	
	public IProductInterfaceDao getProductInterfaceDao() {
		return productInterfaceDao;
	}
	
	@Resource
	public void setProductInterfaceDao(IProductInterfaceDao productInterfaceDao) {
		this.productInterfaceDao = productInterfaceDao;
	}

	@Override
	public ProductInterface add(ProductInterface productInterface) {
		ProductInterface p = productInterfaceDao.loadProductInterface(productInterface.getProductname(), productInterface.getIfparameter());
		if (p!=null) {
			throw new ProductInterfaceException("要添加的接口已经存在！");
		}
		return (ProductInterface)productInterfaceDao.add(productInterface);
	}

	@Override
	public void update(ProductInterface productInterface) {
		productInterfaceDao.update(productInterface);
	}

	@Override
	public void delete(int ifId) {
		productInterfaceDao.delete(ProductInterface.class, ifId);
	}

	@Override
	public ProductInterface load(int ifId) {
		return (ProductInterface)productInterfaceDao.load(ProductInterface.class,ifId);
	}

	@Override
	public PageInfo pageList(String hql, int pageNo, int pageCount) {
		if (hql == null) {
			hql = "from ProductInterface";
		}
		if (pageCount == 0) {
			pageCount = 1;
		}
		return productInterfaceDao.getPageList(hql,pageNo,pageCount);
	}

	@Override
	public ProductInterface getProductInterface(String prodactName,
			String parameter) {
		ProductInterface p = productInterfaceDao.loadProductInterface(prodactName, parameter);
		if (p==null) {
			throw new ProductInterfaceException("0");
		}
		return p;
	}
}