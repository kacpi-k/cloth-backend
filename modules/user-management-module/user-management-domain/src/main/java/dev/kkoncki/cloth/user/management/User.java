package dev.kkoncki.cloth.user.management;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
//    private String image; // TODO: add image
    private int gender;
    private Instant createdOn;
}
