package model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private long id;

    private String clientName;

    private String login;

    private String password;

    private String email;

    private Cart cart;
}