package com.itlijunjie.pt.services;

import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.vo.Reource;

/**
 * Created by ljj on 13/12/2016.
 * ReourceService接口
 */
public interface IReourceService {

    Reource add(Reource reource);

    PageInfo pageList(String hql, int pageNo, int pageCount);

    void delete(int id);

}
