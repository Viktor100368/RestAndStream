package testApi;

public class RequestSusses {
    private String email;
    private String password;
    RequestSusses(String email,String password){
        this.email=email;
        this.password=password;
    }
    RequestSusses(){}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
