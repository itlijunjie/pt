package com.slideviewsoft.pt.services;

import com.slideviewsoft.pt.util.PageInfo;
import com.slideviewsoft.pt.vo.ProductInterface;

public interface IProductInterfaceService {
	public abstract ProductInterface add(ProductInterface productInterface);
	public abstract void update(ProductInterface productInterface);
	public abstract void delete(int ifId);
	public abstract ProductInterface load(int ifId);
	public PageInfo pageList(String hql, int pageNo, int pageCount);
	public abstract ProductInterface getProductInterface(String prodactName , String parameter);
}
