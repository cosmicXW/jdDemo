package jd.service;

import jd.po.Result;

public interface SearchService {

    Result searchProduct(String queryString,String catalog_name,String price,Integer page,String sort);
}
