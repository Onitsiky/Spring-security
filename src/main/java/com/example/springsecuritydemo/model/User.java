package com.example.springsecuritydemo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "\"user\"")
//Pour faire des guillemets dans des guillemets, il faut mettre un \
@AllArgsConstructor
@NoArgsConstructor
@Data
// c'est @Getter + @Setter
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    //@Transient pour créer un attribut mais n'est pas persisté dans la base
    private String lastName;

    @Email(message = "tena sa mba mety ity e-mail an'droky ity.")
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    private String password;
}
