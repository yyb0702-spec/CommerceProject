package commerce;

public class Customer {

    private String u_name;
    private String u_email;
    private String u_grade;


    public Customer(String u_name, String u_email, String u_grade){
        this.u_name = u_name;
        this.u_email = u_email;
        this.u_grade = u_grade;

    }

    public String getU_name(){
        return u_name;
    }
    public String getU_email(){
        return u_email;
    }
    public String getU_grade(){
        return u_grade;
    }



}
