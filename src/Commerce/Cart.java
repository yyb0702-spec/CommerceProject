package Commerce;

import java.util.ArrayList;
import java.util.List;

//Cart안에 상품을 관리한다
public class Cart {

    private Product product;
    private int p_amount;


    public Cart(Product product,int p_amount)
    {
        this.product = product;
        this.p_amount = p_amount;
    }

    public String getName()
    {
        return product.getName();
    }
    public int getPrice()
    {
        return product.getPrice();
    }
    public String getInfo()
    {
        return product.getInfo();
    }
    public int getAmount()
    {
        return product.getAmount();
    }

    public void setAmount(int amount)
    {
        product.setAmount(amount);
    }

    public int getCartamount(){
        return p_amount;
    }
    public void increaseQuantity()
    {
        this.p_amount++;
    }




}
