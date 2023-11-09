package com.gomobile.integratedService.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


@Data
public class User {
    @Id
    private String id;
    private String uid;
    @Indexed(unique = true)
    private String email;
    private Integer credit;

    public User(String uid, String email, Integer credit) {
        this.uid = uid;
        this.email = email;
        this.credit = credit;
    }
}
