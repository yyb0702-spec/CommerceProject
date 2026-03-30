package Commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem { //커머스플랫폼의 상품관리 사용자입력처리

    //속
    private List<Category> categories;

    Scanner sc = new Scanner(System.in);

    //생
    public CommerceSystem(List<Category> categories)
    {
        this.categories = categories;
    }

    //기
    public void start() {

        Cartlist cartlist = new Cartlist();
        List<Product> products = null;  //변수 스코프 List 값은 null 포함한다
        List<Cart> c_list = cartlist.getCartlist();

        boolean bool = true;
        while (bool) {
            /// //////-------------------------------------------------------------------------------메인메뉴출력
            System.out.println("[ 실시간 커머스 플랫폼 - 메인 ]");
            for (int i = 0; i < categories.size(); i++) { //List 카테고리
                Category category = categories.get(i);

                System.out.println(
                        i + 1 + "." + category.getCategory()); //category 이름 getter
            }

            if(!c_list.isEmpty()) /// //////----------------------------------------------------------장바구니(비어있을때 보이지않게)
            {
                System.out.println("4.장바구니 확인");
                System.out.println("5.주문 취소");
            }

            System.out.println("0.종료하기");
            int num = sc.nextInt();
            sc.nextLine();

            /// //////---------------------------------------------------------------------------------메인메뉴출력
        //-----------------0-------------------메뉴시작-----------------------------------------------------------------
            if (num == 0) {
                break;
            }
            if (num > 0 && num <= categories.size()) {

                Category catenum = categories.get(num - 1); // 인덱스 0부터시작 선택
                products = catenum.getProducts();//선택된 인덱스의 product List 출력
                System.out.println("[ 실시간 커머스 플랫폼 - " + catenum.getCategory() + " ]");
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    System.out.printf("%-15s | %,10d | %s%n", //이름왼쪽정렬(15칸) | 숫자오른쪽정렬(10칸) | %s문자열%s줄바꿈
                            i + 1 + "." + product.getName(),
                            product.getPrice(),
                            product.getInfo());
                }
            }
            if(num == 4){//장바구니확인
                if(!c_list.isEmpty())
                {
                    System.out.println("[ 장바구니 내역 ]");
                    cartlist.showCartlist();
                    System.out.println("[ 총 주문 금액 ]");
                    System.out.println(cartlist.totalPrice());

                } else {
                    System.out.println("장바구니가 비어있습니다");
                    continue;
                }
                System.out.println("1.주문 확정 \t 2.메인으로 돌아가기");

                int total = sc.nextInt();

                if(total == 1){

                    System.out.println("주문이 완료되었습니다! 총 금액 : " + cartlist.totalPrice());
                    cartlist.updateAmount();
                    continue;
                }
            }
            if(num == 5){       //장바구니확인
                cartlist.showCartlist();
                System.out.println("취소할 상품번호 선택 \t 0.메인으로 돌아가기");
                int cartidx = sc.nextInt();
                cartlist.removeCartlist(cartidx);
            }
            else {
                System.out.println("잘못된 입력입니다");
            }
            System.out.println("0.종료하기");

            //------------------------------------------------메뉴끝---------------------------------------------------
            int num2 = sc.nextInt();
            sc.nextLine();
            if (num2 == 0) {
                break;
            }
            if (num2 > 0 && num2 < products.size()) {
                Product product = products.get(num2 - 1); //List에서 선택된 product의 정보 출력 index 0 부터 시작
                System.out.printf("선택한 상품 : " + "%10s | %,10d | %s | "+"재고 : " +  "%,1d\n", //이름왼쪽정렬(10칸) | 숫자오른쪽정렬(10칸) | %s문자열 | 오른쪽한칸
                        product.getName(),
                        product.getPrice(),
                        product.getInfo(),
                        product.getAmount());

            }else{System.out.println("잘못된 입력입니다");}

            System.out.println("1.장바구니에 추가하기 2.돌아가기");
            int addCart = sc.nextInt();
            if(addCart == 1) {
                if (num2 > 0 && num2 < products.size()) {
                    Product product = products.get(num2 - 1); //List에서 선택된 product의 정보 출력 index 0 부터 시작
                    System.out.println(product.getName() + "가 장바구니에 추가되었습니다");
                    cartlist.setCartList(product); //카트리스트에 이름,가격 저장
                    if (product.getAmount() <= 1)
                    {
                        System.out.println("수량이 얼마없습니다");//수량이 1이하이면 경고메세지 표기
                    }
                }
            }
            if(addCart == 2){
                break;
            }

        }

    }

}

