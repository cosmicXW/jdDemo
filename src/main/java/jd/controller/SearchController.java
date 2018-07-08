/**
 * @author Plan b
 * @date2018/7/8 简述：
 */


package jd.controller;

import jd.po.Result;
import jd.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/list.action")
    public String list(Model model, String queryString, String catalog_name,
                       String price, Integer page, String sort){


        // 1.搜索商品
        Result result = this.searchService
                .searchProduct(queryString, catalog_name, price, page, sort);

        // 2.响应搜索结果数据
        model.addAttribute("result", result);

        // 3.参数数据回显
        model.addAttribute("queryString", queryString);
        model.addAttribute("catalog_name",catalog_name );
        model.addAttribute("price", price);
        model.addAttribute("sort",sort );
        return "product_list";
    }
}
