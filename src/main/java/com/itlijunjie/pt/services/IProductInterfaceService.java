package com.itlijunjie.pt.services;

import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.vo.ProductInterface;

public interface IProductInterfaceService {

    ProductInterface add(ProductInterface productInterface);

    void update(ProductInterface productInterface);

    void delete(int ifId);

    ProductInterface load(int ifId);

    PageInfo pageList(String hql, int pageNo, int pageCount);

    ProductInterface getProductInterface(String prodactName, String parameter);
}
