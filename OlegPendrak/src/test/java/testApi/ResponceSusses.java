package testApi;

public class ResponceSusses {
    private int id;
    private String token;

    public ResponceSusses(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public ResponceSusses() {
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
