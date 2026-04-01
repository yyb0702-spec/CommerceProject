package Commerce;

public enum Grade {
    BRONZE("BRONZE",0),
    SILVER("SILVER", 5),
    GOLD("GOLD" , 10),
    PLATINUM("PLATINUJM", 15);


    private String gradename;
    private int discount;

    Grade(String grade, int discount) {
        this.gradename = grade;
        this.discount = discount;
    }

    public String getGrade()
    {
        return gradename;
    }
    public int getDiscount()
    {
        return discount;
    }

    public static void showGrade() {
        System.out.println("고객 등급을 입력해주세요");
        for (int i = 0; i < Grade.values().length; i++) {
            Grade grade = Grade.values()[i];
            System.out.println(i + 1 + "." + grade.getGrade() + ":" + grade.getDiscount() + "할인");
        }
    }

        public static Grade discountIdx(int idx){
            if (idx >= 1 && idx <= Grade.values().length) {
                return Grade.values()[idx - 1];
            }
            return null;
        }

        }