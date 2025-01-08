package dev.kkoncki.cloth.user.management;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private int gender;

    @Column(name = "created_on")
    private Instant createdOn;

    @ElementCollection
    @CollectionTable(name = "user_favorite_products", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "product_id")
    private List<String> favoriteProductIds;

    public UserEntity(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.createdOn = user.getCreatedOn();
        this.favoriteProductIds = user.getFavoriteProductIds();
    }
}
