package Commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem { //커머스플랫폼의 상품관리 사용자입력처리

    //속
    private List<Category> categories;

    Scanner sc = new Scanner(System.in);

    //생
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    //기
    public void start() {


        boolean bool = true;
        while (bool) {
            System.out.println("[ 실시간 커머스 플랫폼 - 메인 ]");
            for (int i = 0; i < categories.size(); i++) { //List 카테고리
                Category category = categories.get(i);

                System.out.println(
                        i + 1 + "." + category.getCategory()); //category 이름 getter
            }
            System.out.println("0.종료하기");
            int num = sc.nextInt();
            sc.nextLine();

            if (num == 0) {
                break;
            }
            List<Product> products = null; //변수 스코프 List 값은 null 포함한다
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
            } else {
                System.out.println("잘못된 입력입니다");
            }
            System.out.println("0.종료하기");
            int num2 = sc.nextInt();
            sc.nextLine();
            if (num2 == 0) {
                break;
            }
            if (num2 > 0 && num2 < products.size()) {
                Product product = products.get(num2 - 1); //List에서 선택된 product의 정보 출력 index 0 부터 시작
                System.out.printf("선택한 상품 : " + "%10s | %,10d | %s | "+"재고 : " +  "%,1d", //이름왼쪽정렬(10칸) | 숫자오른쪽정렬(10칸) | %s문자열 | 오른쪽한칸
                        product.getName(),
                        product.getPrice(),
                        product.getInfo(),
                        product.getAmount());

                sc.nextLine();
            }else{System.out.println("잘못된 입력입니다");}


//            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
//            List<Product> electronic = selectedCategory.getProducts();
//            for (int i = 0; i < electronic.size(); i++) { //for문 index선택가능 foreach index선택불가
//                Product product = electronic.get(i);
//
//                System.out.printf("%-15s | %,10d | %s%n", //이름왼쪽정렬(15칸) | 숫자오른쪽정렬(10칸) | %s문자열%s줄바꿈
//                        i + 1 + "." + product.getName(),
//                        product.getPrice(),
//                        product.getInfo());
//            }
//            System.out.println("0.종료하기");
//            int menu = sc.nextInt();
//            sc.nextLine();
//
//            switch (menu) {
//                case 1:
//                    Product product = electronic.get(0);
//                    System.out.printf("%-15s | %,10d | %s%n",
//                            1 + "." +
//                                    product.getName(),
//                            product.getPrice(),
//                            product.getInfo());
//
//                    sc.nextLine();
//                    break;
//                case 2:
//                    product = electronic.get(1);
//                    System.out.printf("%-15s | %,10d | %s%n",
//                            2 + "." +
//                                    product.getName(),
//                            product.getPrice(),
//                            product.getInfo());
//
//                    sc.nextLine();
//                    break;
//                case 3:
//                    product = electronic.get(2);
//                    System.out.printf("%-15s | %,10d | %s%n",
//                            2 + "." +
//                                    product.getName(),
//                            product.getPrice(),
//                            product.getInfo());
//
//                    sc.nextLine();
//                    break;
//                case 4:
//                    product = electronic.get(3);
//                    System.out.printf("%-15s | %,10d | %s%n",
//                            4 + "." +
//                                    product.getName(),
//                            product.getPrice(),
//                            product.getInfo());
//
//                    sc.nextLine();
//                    break;
//                case 0:
//                    System.out.println("종료합니다");
//                    bool = false;
        }

    }
}

