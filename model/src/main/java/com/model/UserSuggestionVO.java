package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "user_suggestion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSuggestionVO implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_suggestion_id", updatable = false, nullable = false)
    private UUID userSuggestionId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserVO user;

    private String name;

    private String email;

    private String suggestion;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
