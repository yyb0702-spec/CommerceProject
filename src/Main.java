import commerce.Category;
import commerce.CommerceSystem;
import commerce.Product;

import java.util.ArrayList;
import java.util.List;

//데이터는 Product.java , 흐름제어 CommerceSystem.java , 시작점 Main
public class Main {
    public static void main(String[] args) {

        List<Category> categories = new ArrayList<>();

        Category electronic = new Category("전자제품");
        Category cloth = new Category("의류");
        Category food = new Category("음식");

        categories.add(electronic);
        categories.add(cloth);
        categories.add(food);

        electronic.setProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 20));
        electronic.setProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 15));
        electronic.setProduct(new Product("MackBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10));
        electronic.setProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 40));

        cloth.setProduct(new Product("아디다스 저지 세트", 100000, "운동할때 입기 좋아요", 25));
        cloth.setProduct(new Product("세미 와이드 팬츠", 40000, "어디에나 어울리는 편한 바지", 35));
        cloth.setProduct(new Product("후아유 후드티", 60000 , "누구나 하나쯤 가지고있는 핫템", 45));
        cloth.setProduct(new Product("라코스테 PK 티셔츠", 75000, "곧 다가올 여름을 대비하세요", 55));

        food.setProduct(new Product("초밥 세트", 14000, "달인이 당일 만든 초밥", 60));
        food.setProduct(new Product("계란 30구", 13000, "목장에 직접가져온 신선한 계란", 70));
        food.setProduct(new Product("순살 후라이드 치킨", 23000, "바삭바삭 크리스피", 57));
        food.setProduct(new Product("페페로니 피자", 20000, "페페로니를 듬뿍 넣었어요", 33));

        CommerceSystem commerce = new CommerceSystem(categories);


        commerce.start();
    }

}
