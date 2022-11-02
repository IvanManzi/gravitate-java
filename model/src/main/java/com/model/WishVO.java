package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "wish")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "wish_id", updatable = false)
    private UUID wishId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserVO user;

    @Column(nullable = false)
    private String withType;

    @Column(nullable = false)
    private String comment;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "wish",orphanRemoval = true)
    Set<UserWishVO> userWishes = new HashSet<>();

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
