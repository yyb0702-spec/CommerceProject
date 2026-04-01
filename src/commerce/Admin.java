package commerce;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Admin {

    private static final String ADMINPASSWORD = "abc123";
    private List<Category> categories;
    private CartList cartlist;
    Scanner sc = new Scanner(System.in);

    public Admin(List<Category> categories, CartList cartlist) {
        this.categories = categories;
        this.cartlist = cartlist;
    }

    public boolean checkPassword() {
        int check = 0;
        while (check < 3) {
            System.out.println("관리자 비밀번호를 입력해주세요 : ");
            String password = sc.nextLine();

            if (ADMINPASSWORD.equals(password)) {
                return true;
            } else {
                check++;
                if (check == 3) {
                    System.out.println("비밀번호 3회 실패로 메인 메뉴로 돌아갑니다");
                } else {
                    System.out.println("비밀번호가 틀렸습니다 다시 입력해주세요");
                }
            }
        }
        return false;
    }

    public void startAdmin() {

        while (true) {
            System.out.println("[ 관리자모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");

            int adminChoice = 0;
            try {
                adminChoice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("잘못된 입력값입니다");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (adminChoice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    showAllProduct();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다 다시 입력해주세요");
                    break;
            }
        }
    }

    public void addProduct() {

        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        IntStream.range(0, categories.size()) //스트림은 index를 주지않는다
                .forEach(i -> {
                    System.out.println((i + 1) + "." + categories.get(i).getCategory());
                });
        int category = sc.nextInt();
        sc.nextLine();

        if (category < 1 || category > categories.size()) {
            System.out.println("잘못된 카테고리 번호를 입력했습니다");
            return;
        }

        Category selectedCategory = categories.get(category - 1); // 인덱스 0부터시작 선택
        System.out.println("상품명을 입력해주세요 : ");
        String name = sc.nextLine();
        System.out.println("가격을 입력해주세요 : ");
        int price = 0;
        try {
            price = sc.nextInt();
        } catch (Exception e) {
            System.out.println("올바른 상품가격을 입력해주세요");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        System.out.println("상품 설명을 입력해주세요 : ");
        String info = sc.nextLine();

        System.out.println("재고수량을 입력해주세요 : ");
        int amount = 0;
        try {
            amount = sc.nextInt();
        } catch (Exception e) {
            System.out.println("올바른 재고 수량을 입력해주세요");
            sc.nextLine();
            return;
        }

        sc.nextLine();
        Product newproduct = new Product(name, price, info, amount);

        selectedCategory.setProduct(newproduct);

        System.out.println("상품이 성공적으로 추가되었습니다!");
    }

    public void deleteProduct() {
        System.out.println("어느 카테고리에 상품을 삭제하시겠습니까?");
        IntStream.range(0, categories.size()) //스트림은 index를 주지않는다
                .forEach(i -> {
                    System.out.println((i + 1) + "." + categories.get(i).getCategory());
                });
        int selectcategory = 0;
        try {
            selectcategory = sc.nextInt();
        } catch (Exception e) {
            System.out.println("입력이 잘못되었습니다");
            sc.nextLine();
            return;
        }
        sc.nextLine();
        if (selectcategory > 0 && selectcategory <= categories.size()) {
            Category catenum = categories.get(selectcategory - 1); // 인덱스 0부터시작 선택
            List<Product> products = catenum.getProducts();

            System.out.println("[ 실시간 커머스 플랫폼 - " + catenum.getCategory() + " ]");

            IntStream.range(0, products.size())
                    .forEach(i -> {
                        Product product = products.get(i);
                        System.out.printf("%-15s | %,10d | %s%n",
                                (i + 1) + "." + product.getName(),
                                product.getPrice(),
                                product.getInfo());
                    });
            int num3 = 0;
            try {
                num3 = sc.nextInt();
            } catch (Exception e) {
                System.out.println("입력이 잘못되었습니다");
                sc.nextLine();
                return;
            }
            sc.nextLine();
            while (true) {
                if (num3 > 0 && num3 <= products.size()) {
                    Product product = products.get(num3 - 1); //List에서 선택된 product의 정보 출력 index 0 부터 시작
                    System.out.printf("선택한 상품 : " + "%10s | %,10d | %s | " + "재고 : " + "%,1d\n", //이름왼쪽정렬(10칸) | 숫자오른쪽정렬(10칸) | %s문자열 | 오른쪽한칸
                            product.getName(),
                            product.getPrice(),
                            product.getInfo(),
                            product.getAmount());

                    System.out.println("1.삭제하기 2.돌아가기");
                    int num4 = 0;
                    try {
                        num4 = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("올바른 입력값을 넣어주세요");
                        sc.nextLine();
                    }
                    sc.nextLine();
                    if (num4 == 1) {
                        System.out.println("정말 삭제하시겠습니까?");
                        System.out.println("1.삭제하기 2.취소");
                        int num5 = 0;
                        try {
                            num5 = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("올바른 입력값을 넣어주세요");
                            sc.nextLine();
                        }
                        sc.nextLine();
                        if (num5 == 1) {
                            products.remove(product);
                            deleteCart(product);
                            System.out.println("선택 하신 제품" + product.getName() + "이 삭제되었습니다");
                            sc.nextLine();
                            break;
                        } else if (num5 == 2) {
                            break;
                        } else {
                            System.out.println("다시입력해주세요");
                            continue;
                        }
                    }
                    if (num4 == 2) {
                        break;
                    }
                }
            }
        } else {
            System.out.println("잘못된 카테고리 번호입니다");
            return;
        }
    }


    public void updateProduct() {
        System.out.println("수정할 상품명을 입력해주세요 : ");
        String fixPname = sc.nextLine();
        Product find = categories.stream()// 상위 리스트
                .flatMap(category -> category.getProducts().stream())//하위 리스트
                .filter(product -> product.getName().equals(fixPname))//변수fixPname과 같은걸찾는다
                .findFirst() //반환
                .orElse(null); //없으면 null
        if (find != null) {
            System.out.println("찾은 상품: " + find.getName());
        } else {
            System.out.println("상품을 찾을 수 없습니다");
            return;
        }
        System.out.println("수정할 항목을 선택해주세요 : ");
        System.out.println("1. 가격");
        System.out.println("2. 정보");
        System.out.println("3. 수량");
        int fixnum = 0;
        try {
            fixnum = sc.nextInt();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        System.out.println("수정할 항목을 선택해주세요 : ");
        if (fixnum == 1) {
            System.out.println("현재 가격 : " + find.getPrice());
            System.out.println("새로운 가격을 입력해주세요 : ");
            int newPrice = 0;
            try {
                newPrice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("잘못된 입력값입니다");
                sc.nextLine();
                return;
            }
            sc.nextLine();
            find.setPrice(newPrice);
        } else if (fixnum == 2) {
            System.out.println("현재 정보 : " + find.getInfo());
            System.out.println("새로운 정보를 입력해주세요 : ");
            String newInfo = sc.nextLine();
            find.setInfo(newInfo);
        } else if (fixnum == 3) {
            System.out.println("현재 수량 : " + find.getAmount());
            System.out.println("새로운 수량을 입력해주세요 : ");
            int newAmount = 0;
            try {
                newAmount = sc.nextInt();
            } catch (Exception e) {
                System.out.println("잘못된 입력값입니다");
                sc.nextLine();
                return;
            }
            sc.nextLine();
            find.setAmount(newAmount);
        } else {
            System.out.println("잘못된 입력입니다");
        }
    }

    public void showAllProduct() {

        categories.stream() // 리스트카테고리 스트림
                .forEach(category -> { //출력
                    System.out.println(category.getCategory());
                    category.getProducts().stream() //프로덕트 스트림
                            .forEach(product -> System.out.printf("%-25s | %,10d | %s%n", //출력
                                    product.getName(),
                                    product.getPrice(),
                                    product.getInfo())
                            );
                });
        System.out.println("엔터를 누르면 관리자메뉴로 복귀합니다");
        sc.nextLine();
//        for (Category category : categories) {
//            System.out.println(category.getCategory());
//            for (Product product : category.getProducts()) {
//                System.out.printf("%-25s | %,10d | %s%n",
//                        product.getName(),
//                        product.getPrice(),
//                        product.getInfo());
//            }
//        }

    }

    public void deleteCart(Product product) {
        List<Cart> bag = cartlist.getCartlist();
        bag.removeIf(cart -> cart.getName().equals(product.getName())); //조건삭제 stream
    }

}
