package ZooServer;

public class Login {

    private String login;
    private String pas;

    public Login(String login, String pas) {
        this.login = login;
        this.pas = pas;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }
}
