package Commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);
        System.out.println("[ 장바구니 내역 ]");
        for (int i = 0; i < cartlist.size(); i++) {
            Cart product = cartlist.get(i);
            System.out.printf("%10s | %,10d | %s | " + "수량 : " + "%,1d\n",
                    i + 1 + "." + product.getName(),
                    product.getPrice(),
                    product.getInfo(),
                    product.getAmount());

            System.out.println("[ 총 주문 금액 ]");
            System.out.println(totalPrice());
        }
        System.out.println("1.주문 확정 \t 2.메인으로 돌아가기");

        int total = sc.nextInt();

        if (total == 1) {

            System.out.println("주문이 완료되었습니다! 총 금액 : " + totalPrice());
            updateAmount();
        }

    }


    public void removeCartlist() {
        Scanner sc = new Scanner(System.in);

        System.out.println("[ 장바구니 내역 ]");
        for (int i = 0; i < cartlist.size(); i++) {
            Cart product = cartlist.get(i);
            System.out.printf("%10s | %,10d | %s | " + "수량 : " + "%,1d\n",
                    i + 1 + "." + product.getName(),
                    product.getPrice(),
                    product.getInfo(),
                    product.getAmount());
        }
        System.out.println("취소할 상품번호 선택 \t 0.메인으로 돌아가기");
        int cartidx = sc.nextInt();
        cartlist.remove(cartidx - 1);
        System.out.println("취소 완료");
        if (cartidx == 0) {
            return;
        }
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
