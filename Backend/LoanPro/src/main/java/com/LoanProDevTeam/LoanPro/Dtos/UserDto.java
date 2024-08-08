package com.LoanProDevTeam.LoanPro.Dtos;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int userId;

    private String userUuid;

    @Size(min=3,max=20,message = "Invalid Name")
    private String name;

    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "invalid email")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @Size(min=4,max=6,message = "invalid gender")
    private String gender;

    @Column(nullable = false)
    @Pattern(regexp="(^$|[0-9]{10})",message = "Invalid Mobile Number") // Validation for a 10-digit mobile number
    private String phoneNo;

    private Date dateOfBirth;

    private int age;

    private String imageName;

    private String createdBy;

    private String updatedBy;

    private Date createdDate;

    private Date updatedDate;
}
