package Commerce;

public class Customer {

    private String name;
    private String email;
    private String grade;


    public Customer(String name, String email,String grade){
        this.name = name;
        this.email = email;
        this.grade = grade;

    }

    public String getCustomername(){
        return name;
    }
    public String getCustomeremail(){
        return email;
    }
    public String getCustomergrade(){
        return grade;
    }



}
