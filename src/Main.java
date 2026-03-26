import Commerce.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 20));
        products.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 15));
        products.add(new Product("MackBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10));
        products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 40));

        boolean bool = true;
        while (bool) {
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            for (int i = 0; i < products.size(); i++) { //for문 index선택가능 foreach index선택불가
                Product product = products.get(i);

                System.out.printf("%-15s | %,10d | %s%n", //이름왼쪽정렬(15칸) | 숫자오른쪽정렬(10칸) | %s문자열%s줄바꿈
                        i + 1 + "." + product.getName(),
                        product.getPrice(),
                        product.getInfo());
            }
            System.out.println("0.종료하기");
            int menu = sc.nextInt();
            sc.nextLine();

            switch(menu){
                case 1:
                    Product product = products.get(0);
                    System.out.printf("%-15s | %,10d | %s%n",
                    1 + "." +
                    product.getName(),
                    product.getPrice(),
                    product.getInfo());

                    sc.nextLine();
                    break;
                case 2:
                    product = products.get(1);
                    System.out.printf("%-15s | %,10d | %s%n",
                            2 + "." +
                                    product.getName(),
                            product.getPrice(),
                            product.getInfo());

                    sc.nextLine();
                    break;
                case 3:
                    product = products.get(2);
                    System.out.printf("%-15s | %,10d | %s%n",
                            2 + "." +
                                    product.getName(),
                            product.getPrice(),
                            product.getInfo());

                    sc.nextLine();
                    break;
                case 4:
                    product = products.get(3);
                    System.out.printf("%-15s | %,10d | %s%n",
                            4 + "." +
                                    product.getName(),
                            product.getPrice(),
                            product.getInfo());

                    sc.nextLine();
                    break;
                case 0:
                    System.out.println("종료합니다");
                    bool = false;
            }

        }
    }

}
