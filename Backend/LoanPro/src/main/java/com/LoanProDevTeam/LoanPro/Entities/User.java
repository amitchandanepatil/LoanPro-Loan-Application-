package com.LoanProDevTeam.LoanPro.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userUuid;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name="password",length = 500)
    private String password;

    private String gender;

    private String phoneNo;

    private Date dateOfBirth;

    private int age;

    private String imageName;

    private String createdBy;

    private String updatedBy;

    private Date createdDate;

    private Date updatedDate;
}
