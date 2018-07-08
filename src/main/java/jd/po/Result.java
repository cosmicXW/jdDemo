package jd.po;


import java.util.List;

/**
 * @ClassName: Result
 * @Description: 搜索结果实体类对象
 * @author 传智 小杨老师  
 * @date 2018-7-4 下午12:19:05 
 *
 */
public class Result {

    //	当前页
    private Integer curPage;
    //	页数
    private Integer pageCount;
    //	记录数
    private Integer recordCount;
    //	商品结果集合list
    private List<Product> productList;
    /**
     * @return the curPage
     */
    public Integer getCurPage() {
        return curPage;
    }
    /**
     * @param curPage the curPage to set
     */
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }
    /**
     * @return the pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }
    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    /**
     * @return the recordCount
     */
    public Integer getRecordCount() {
        return recordCount;
    }
    /**
     * @param recordCount the recordCount to set
     */
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }
    /**
     * @return the productList
     */
    public List<Product> getProductList() {
        return productList;
    }
    /**
     * @param productList the productList to set
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


}