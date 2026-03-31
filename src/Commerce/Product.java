package Commerce;

import java.util.ArrayList;
import java.util.List;

public class Product { //개별상품관리 전자제품

//    List<Product> products = new ArrayList<>();
//속
    private String name;
    private int price;
    private String info;
    private int amount;
//생
    public Product(String name, int price, String info, int amount) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.amount = amount;

//        products.add(this);
    }
//기
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public String getInfo(){
        return info;
    }
    public int getAmount(){
        return amount;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
    public void setInfo(String info)
    {
        this.info = info;
    }
//    public List<Product> getList()
//    {
//        return products;
//    }
    public void setAmount(int amount)
    {
        this.amount = amount;
    }







}
