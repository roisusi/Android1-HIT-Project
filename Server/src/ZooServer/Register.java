package ZooServer;

import java.util.List;

public class Register {
    private String name;
    private String password;
    private String admin;
    private String email;
    private List<String> register;

    public Register(String name, String password, String admin, String email) {
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> send() {
        register.add(getName());
        register.add(getPassword());
        register.add(getAdmin());
        register.add(getEmail());
        return register;
    }

}
