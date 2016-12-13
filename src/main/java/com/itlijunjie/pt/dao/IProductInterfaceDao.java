package com.itlijunjie.pt.dao;

import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.vo.ProductInterface;

public interface IProductInterfaceDao extends IPageBaseDAO {
	ProductInterface loadProductInterface(String productName , String parameter);
}