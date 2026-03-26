package Commerce;

import java.util.ArrayList;
import java.util.List;

public class Product {

    List<Product> products = new ArrayList<>();

    private String name;
    private int price;
    private String info;
    private int amount;

    public Product(String name, int price, String info, int amount) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.amount = amount;

        products.add(this);
    }

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
    public List<Product> getList()
    {
        return products;
    }







}
