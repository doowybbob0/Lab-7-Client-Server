package utils;
/**
 * Именно объекты этого класса находятся в коллекции пользователей
 */
public class User {
    private String login;
    private String password;
    private Long id;
    public User(String user_login,String user_password,Long user_id){
        this.id=user_id;
        this.login=user_login;
        this.password=user_password;
    }
    public Long getId() { return id; }

    public String getLogin() { return login; }

    public String getParol() {return password; }
}
