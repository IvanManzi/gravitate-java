package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id", updatable = false)
    private UUID userId;

    private String email;

    private String alternateEmail;

    private String firstName;

    private String lastName;

    private String country;

    private Date dateOfBirth;

    private String phoneNumber;

    private String profilePicturePath;

    private String contractPath;

    private String employmentStatus;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleVO role;

    private String responsibility;

    private Integer billing;

    private String bankName;

    private String accountNumber;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
    Set<UserSkillVO> userSkills = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.REMOVE,orphanRemoval = true)
    Set<UserBlogVO> userBlogs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.REMOVE,orphanRemoval = true)
    Set<UserTopicVO> userTopics = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
    Set<UserSuggestionVO> userSuggestions = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
    Set<WishVO> userWishes = new HashSet<>();

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
