package cz.zjor.toys.polychat.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String username;

    public User(String username) {
        id = UUID.randomUUID().toString();
        this.username = username;
    }

}
