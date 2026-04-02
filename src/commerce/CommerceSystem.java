package commerce;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommerceSystem {


    //속
    private List<Category> categories;
    private Admin admin;
    private CartList cartlist;
    Scanner sc = new Scanner(System.in);


    //생
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
        this.cartlist = new CartList();
        this.admin = new Admin(categories, cartlist);
    }

    //기
    public void start() {

        while (true) {
            boolean useCart = !cartlist.getCartlist().isEmpty();
            //------------------------------------------------------------------------------------------------메인메뉴출력
            System.out.println("[ 실시간 커머스 플랫폼 - 메인 ]");
            showCategory(categories);
            if (useCart) {
                System.out.println("4.장바구니 확인");
                System.out.println("5.주문 취소");
            }
            System.out.println("6.관리자모드");
            System.out.println("0.종료하기");
            int num = 0;
            try {
                num = sc.nextInt();
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다");
                sc.next();
                return;
            }
            sc.nextLine();
            ///---------------------------------------------------------------------------------------------메인메뉴출력끝

            //----------------------------------메뉴시작-----------------------------------------------------------------
            if (num > 0 && num <= categories.size()) {
                Category catenum = categories.get(num - 1); // 인덱스 0부터시작 선택
                showProductMenu(catenum);
            } else if (num == 4 && useCart) {//장바구니확인
                cartlist.displayCartlist();
            } else if (num == 5 && useCart) {       //장바구니확인
                cartlist.removeCartlist();
            } else if ((num == 4 || num == 5) && cartlist.getCartlist().isEmpty()) {
                System.out.println("장바구니가 비었습니다");
            }
            //-----------------------------------------장바구니끝--------------------------------------------------------
            //-------------------------------------- 관리자 -------------------------------------------------------------
            else if (num == 6) {
                if (admin.checkPassword()) {
                    admin.startAdmin();
                }
            } else if (num == 0) {
                break;
            } else {
                System.out.println("잘못된 입력입니다");
                return;
            }
        }
    }

    //------------------------------------------------메뉴끝---------------------------------------------------


    public void showCategory(List<Category> categories) {//상위메뉴

//        for (int i = 0; i < categories.size(); i++) { //List 카테고리
//            Category category = categories.get(i);
//
//            System.out.println(
//                    i + 1 + "." + category.getCategory()); //category 이름 getter
//        }

        IntStream.range(0, categories.size()) //스트림은 index를 주지않는다
                .forEach(i -> {
                    System.out.println((i + 1) + "." + categories.get(i).getCategory());
                });
    }

    public void showProductMenu(Category category) {

        List<Product> products = category.getProducts();

        System.out.println("1.전체 상품 보기");
        System.out.println("2.가격대별 필터링(100만원 이하)");
        System.out.println("3.가격대별 필터링(100만원 초과)");
        System.out.println("0.돌아가기");

        int menunum = 0;
        try {
            menunum = sc.nextInt();
        } catch (Exception e) {
            System.out.println("입력값이 잘못되었습니다");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        if (menunum == 1) {
            showProducts(category);
        } else if (menunum == 2) {
            downPrice(category);
        } else if (menunum == 3) {
            upPrice(category);
        } else if (menunum == 0) {
        return;}
        else{
            System.out.println("잘못된 입력값입니다");
            return;
        }
        System.out.println("0.돌아가기");

        int num2 = 0;
        try {
            num2 = sc.nextInt();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        if (num2 > 0 && num2 <= products.size()) {
            Product product = products.get(num2 - 1); //List에서 선택된 product의 정보 출력 index 0 부터 시작
            detailMenu(product);

            System.out.println("1.장바구니에 추가하기 2.돌아가기");
            int addCart = 0;
            try {
                addCart = sc.nextInt();
            } catch (Exception e) {
                System.out.println("올바른 입력을해주세요");
                sc.nextLine();
                return;
            }
            if (addCart == 1) {
                addCart(product);
                sc.nextLine();
            }
            else if(addCart == 2) {
            return;
            }
        }
    }

    public void showProducts(Category category) {//하위메뉴

        List<Product> products = category.getProducts();

        System.out.println("[ 실시간 커머스 플랫폼 - " + category.getCategory() + " ]");

//        for (int i = 0; i < products.size(); i++) {
//            Product product = products.get(i);
//
//            System.out.printf("%-15s | %,10d | %s%n",
//                    (i + 1) + "." + product.getName(),
//                    product.getPrice(),
//                    product.getInfo());
//        }
        IntStream.range(0, products.size()) //스트림은 index를 주지않는다
                .forEach(i -> {
                    Product product = products.get(i);
                    System.out.printf("%-15s | %,10d | %s%n",
                            (i + 1) + "." + product.getName(),
                            product.getPrice(),
                            product.getInfo());
                });
    }

    public void upPrice(Category category) {
        List<Product> upPrice = category.getProducts().stream()
                .filter(product -> product.getPrice() >= 1000000)//변수fixPname과 같은걸찾는다
                .collect(Collectors.toList());

        if(upPrice.isEmpty())
        {
            System.out.println("해당되는 상품이 없습니다");
        }
        else {

            IntStream.range(0, upPrice.size()) //스트림은 index를 주지않는다
                    .forEach(i -> {
                        Product product = upPrice.get(i);
                        System.out.printf("%-15s | %,10d | %s%n",
                                (i + 1) + "." + product.getName(),
                                product.getPrice(),
                                product.getInfo());
                    });
        }
    }

    public void downPrice(Category category) {
        List<Product> downPrice = category.getProducts().stream()
                .filter(product -> product.getPrice() <= 1000000)//변수fixPname과 같은걸찾는다
                .collect(Collectors.toList());
        if(downPrice.isEmpty())
        {
            System.out.println("해당되는 상품이 없습니다");
        }
        else {

            IntStream.range(0, downPrice.size()) //스트림은 index를 주지않는다
                    .forEach(i -> {
                        Product product = downPrice.get(i);
                        System.out.printf("%-15s | %,10d | %s%n",
                                (i + 1) + "." + product.getName(),
                                product.getPrice(),
                                product.getInfo());
                    });
        }

    }

    public void detailMenu(Product product) {
        System.out.printf("선택한 상품 : " + "%10s | %,10d | %s | " + "재고 : " + "%,1d\n", //이름왼쪽정렬(10칸) | 숫자오른쪽정렬(10칸) | %s문자열 | 오른쪽한칸
                product.getName(),
                product.getPrice(),
                product.getInfo(),
                product.getAmount());
    }

        public void addCart(Product product) {
            if (product.getAmount() <= 1) {
                System.out.println("수량이 얼마없습니다");//수량이 1이하이면 경고메세지 표기
                cartlist.setCartList(product); //카트리스트에 이름,가격 저장
                System.out.println(product.getName() + "가 장바구니에 추가되었습니다");
                System.out.println("ENTER 입력시 메뉴로 돌아갑니다");
                sc.nextLine();
        }
            if(product.getAmount() == 0 ){
                System.out.println("품절된 상품 입니다");
                System.out.println("ENTER 입력시 메뉴로 돌아갑니다");
                sc.nextLine();
            }
            else
            {
                cartlist.setCartList(product); //카트리스트에 이름,가격 저장
                System.out.println(product.getName() + "가 장바구니에 추가되었습니다");
                System.out.println("ENTER 입력시 메뉴로 돌아갑니다");
                sc.nextLine();
            }
    }
}

