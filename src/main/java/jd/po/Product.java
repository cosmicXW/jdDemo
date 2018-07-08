package jd.po;

/**
 * @ClassName: Product
 * @Description: 商品实体类对象
 * @author 传智 小杨老师  
 * @date 2018-7-4 下午12:16:45 
 *
 */
public class Product {

    //	商品Id
    private String pid;
    //	商品名称
    private String name;
    //	商品价格
    private String price;
    //	商品图片
    private String picture;
    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }
    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }
    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }
    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}