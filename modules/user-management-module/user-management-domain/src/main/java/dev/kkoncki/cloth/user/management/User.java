package dev.kkoncki.cloth.user.management;

import lombok.*;

import java.time.Instant;
import java.util.List;

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
    private List<String> favoriteProductsIds;
}
