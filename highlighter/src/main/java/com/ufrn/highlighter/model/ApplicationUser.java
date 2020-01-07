package com.ufrn.highlighter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationUser implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'email' is mandatory")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "The field 'username' is mandatory")
    @Column(nullable = false)
    private String username;

    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @Column(nullable = false)
    @Builder.Default
    private String role = "USER";

    @ManyToMany(mappedBy = "applicationUsers")
    private Set<Project> projects;


    public ApplicationUser(@NotNull ApplicationUser applicationUser) {
        this.id = applicationUser.getId();
        this.email = applicationUser.getEmail();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();
    }

}
