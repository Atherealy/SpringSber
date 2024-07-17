package model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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