package cz.zjor.toys.polychat.model;

import cz.zjor.toys.polychat.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String id;

    private User owner;

    private String content;

    private Date born;

    public Message(User owner, String content) {
        id = UUID.randomUUID().toString();
        born = new Date();

        this.owner = owner;
        this.content = content;
    }
}
