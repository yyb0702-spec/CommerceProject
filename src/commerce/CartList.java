package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

//Cart 클래스를 관리한다 목록출력,
public class CartList {

    private List<Cart> cartlist = new ArrayList<>();
    Scanner sc = new Scanner(System.in);


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

    public void displayCartlist() {

        if(cartlist.isEmpty())
        {
            System.out.println("장바구니가 비어있습니다");
            return;
        }
        showCartlist();
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(totalPrice());

        System.out.println("1.주문 확정 \t 2.메인으로 돌아가기");

        int total = 0;
        try {
            total = sc.nextInt();
        } catch (Exception e) {
            System.out.println("잘못된입력입니다");
            sc.nextLine();
            return;
        }
        sc.nextLine();
        if (total == 1) {
            if(orderCart()){
            updateAmount();
            sc.nextLine();
            }
            System.out.println("ENTER 입력시 메뉴로 돌아갑니다");
            sc.nextLine();
        } else if (total == 2) {
            return;
        }
    }


    public void removeCartlist() {
        if(cartlist.isEmpty())
        {
            System.out.println("장바구니가 비어있습니다");
            return;
        }
        showCartlist();
        System.out.println("취소할 상품번호 선택 \t 0.메인으로 돌아가기");
        int cartidx = 0;
        try {
            cartidx = sc.nextInt();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다");
            sc.nextLine();
            return;
        }
        sc.nextLine();
        if(cartidx > 0 && cartidx <= cartlist.size()) {
            cartlist.remove(cartidx - 1);
            System.out.println("취소 완료");
            System.out.println("ENTER 입력시 메인으로 돌아갑니다");
            sc.nextLine();
        }
        else if(cartidx == 0 )
        {
            System.out.println("메인으로 돌아갑니다");
            return;
        }
        else{
            System.out.println("잘못된 입력입니다");
        }
    }


    public double totalPrice() {
        return cartlist.stream()
                .mapToDouble(cart -> cart.getPrice() * cart.getCartamount())
                .sum();
    }

    public void updateAmount() {
        for (Cart cart : cartlist) {
            int updateAmount = 0;
            int amount = cart.getAmount();
            updateAmount = cart.getAmount() - cart.getCartamount();
            cart.setAmount(updateAmount);


            System.out.println(cart.getName() + " 의 수량이 " + amount + " -> " + updateAmount + " 로 변경되었습니다");
        }
        cartlist.clear();
    }

    public boolean orderCart()
    {
        Grade.showGrade();
        int gradeidx = 0;
        try {
            gradeidx = sc.nextInt();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다");
            sc.nextLine();
            return false;
        }
        Grade checkGrade = Grade.discountIdx(gradeidx);
        if (checkGrade == null) {
            System.out.println("잘못된 등급입니다");
            return false;
        }
        System.out.println("주문이 완료되었습니다! 할인전 금액 : " + totalPrice());
        double discountPrice = (totalPrice() / 100) * checkGrade.getDiscount();
        System.out.println("할인된 금액 : " + discountPrice);
        double finalPrice = totalPrice() - discountPrice;
        System.out.println("최종 결제 금액 : " + finalPrice);

        return true;
    }

    public void showCartlist()
    {
        System.out.println("[ 장바구니 내역 ]");
        IntStream.range(0, cartlist.size())
                .forEach(i ->{
                    Cart product = cartlist.get(i);
                    System.out.printf("%10s | %,10d | %s | " + "수량 : " + "%,1d\n",
                            i + 1 + "." + product.getName(),
                            product.getPrice(),
                            product.getInfo(),
                            product.getCartamount());
                });

    }
}
