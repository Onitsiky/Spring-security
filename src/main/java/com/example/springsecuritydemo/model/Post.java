package com.example.springsecuritydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "\"post\"")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant postingDate;

    @NotBlank(message = "Sy mainsy asina content raha 'ty leka !")
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String content;

    @ManyToOne
    private User user;
}
