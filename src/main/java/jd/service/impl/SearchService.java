/**
 * @author Plan b
 * @date2018/7/8 简述：
 */


package jd.service.impl;

import jd.po.Product;
import jd.po.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SearchService implements jd.service.SearchService {

    @Autowired
    public HttpSolrServer httpSolrServer;


    public Result searchProduct(String queryString, String catalog_name, String price, Integer page, String sort) {
//        1.创建搜索对象
        SolrQuery sq = new SolrQuery();
//        1.1.设置查询关键字
        if (StringUtils.isNotBlank(queryString)) {
            sq.setQuery(queryString);
        } else {
            sq.setQuery("");
        }

//        1.2.设置默认搜索域
        sq.set("df", "product_keywords");
//        1.3设置过滤条件
//        商品分类名称
        if (StringUtils.isNotBlank(catalog_name)) {
            catalog_name = "product_catalog_name:" + catalog_name;
        }
//        商品价格0-9
        if (StringUtils.isNotBlank(price)) {
            String[] arr = price.split("-");
            price = "product_price:[" + arr[0]+"To"+arr[1]+"]";
        }
        sq.setFilterQueries(catalog_name, price);

//        1.4设置分页
//        默认搜索第一页
        if (page == null) {
            page=1;
        }
        int pageSize = 10;//每一页显示10个数据
        sq.setStart((page - 1) * pageSize);
        sq.setRows(pageSize);

//        1.5设置排序
//        1表示升序,其他都是降序
        if ("1".equals(sort)) {
            sq.setSort("product_price", SolrQuery.ORDER.asc);
        } else {
            sq.setSort("product_price", SolrQuery.ORDER.desc);
        }

//        1.6设置高亮显示
//        开始高亮图片
        sq.setHighlight(true);
//        添加高亮显示的域
        sq.addHighlightField("product_name");
//        设置html高亮显示的开头和结尾
        sq.setHighlightSimplePre("<font color='red'>");
        sq.setHighlightSimplePost("</font>");

//        2.执行搜索,返回查询响应结果对象
        QueryResponse queryResponse = null;
        try {
            queryResponse = httpSolrServer.query(sq);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

//        3.从QueryResponse中,获取搜索结果数据
//        3.1取出搜索的结果集
        SolrDocumentList resultList = queryResponse.getResults();
//        3.2取出高亮数据
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

//        4.处理结果集
//        4.1创建Result对象
        Result result = new Result();
//        4.2设置当前页数
        result.setCurPage(page);
//        总记录数
        int totals = (int) resultList.getNumFound();
//        计算页数
        int  pageCount = 0;
        if(totals%pageSize == 0){
            pageCount = totals/pageSize;
        }else{
            pageCount = (totals/pageSize)+1;
        }

        result.setPageCount(pageCount);
//        4.3设置总记录数
        result.setRecordCount(totals);
//        4.4设置商品结果集
        List<Product> productList = new ArrayList<Product>();
        for (SolrDocument doc : resultList) {
//            获取商品id
            String id = doc.get("id").toString();
//            获取商品名字
            String pname = "";
            List<String> list = highlighting.get("pid").get("product_name");
            if (list != null && list.size() > 0) {
                pname = list.get(0);
            } else {
                pname = doc.get("product_name").toString();
            }
//            获取商品价格
            String pprice = doc.get("product_price").toString();
//            获取商品图片
            String ppicture = doc.get("product_picture").toString();
//            获取商品对象
            Product product = new Product();
            product.setPid(id);
            product.setName(pname);
            product.setPicture(ppicture);
            product.setPrice(pprice);
            productList.add(product);

        }

        result.setProductList(productList);

        return result;
    }
}
