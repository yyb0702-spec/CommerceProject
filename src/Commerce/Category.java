package Commerce;


import java.util.ArrayList;
import java.util.List;

//Product class 를 관리한다
public class Category {

    //속

    private String categoryname;
    private List<Product> products = new ArrayList<>();


    //생
    public Category(String categoryname){
        this.categoryname = categoryname;
    }

    public String getCategory(){
        return categoryname;
    }

    public List<Product> getProducts(){
        return products;
    }


    public void setProduct(Product product){
    products.add(product);
    }
    }




