package com.aritra.healthbridge.entity;

import com.aritra.healthbridge.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_details")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String userName;

    @Column(nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

}
