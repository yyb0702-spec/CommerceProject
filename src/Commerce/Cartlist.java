package Commerce;

import java.util.ArrayList;
import java.util.List;

//Cart 클래스를 관리한다 목록출력,
public class Cartlist {

    private List<Cart> cartlist = new ArrayList<>();


    //필요한 기능 : 목록조회 , 목록삭제 ,
    public void setCartList(Product product) {

        for (Cart cart : cartlist) {
            if (product.getName().equals(cart.getName())) {
                cart.increaseQuantity();
                return;
            }
        }
        cartlist.add(new Cart(product, 1));
    }


    public List<Cart> getCartlist() {
        return cartlist;
    }

    public void showCartlist() {
        for (int i = 0; i < cartlist.size(); i++) {
            Cart product = cartlist.get(i);
            System.out.printf("%10s | %,10d | %s | " + "수량 : " + "%,1d\n",
                    i + 1 + "." + product.getName(),
                    product.getPrice(),
                    product.getInfo(),
                    product.getAmount());
        }
    }

    public void removeCartlist(int idx) {
        cartlist.remove(idx - 1);
        System.out.println("");
        }


    public double totalPrice() {
        double total = 0;
        for (Cart cart : cartlist) {
            total += cart.getPrice() * cart.getCartamount();

        }
        return total;
    }

    public void updateAmount() {
        int updateAmount = 0;
        for (Cart cart : cartlist) {
            int amount = cart.getAmount();
            updateAmount = cart.getAmount() - cart.getCartamount();
            cart.setAmount(updateAmount);


            System.out.println(cart.getName() + " 의 수량이 " + amount + " -> " + updateAmount + " 로 변경되었습니다");
        }
        cartlist.clear();
    }

}
