package com.ufrn.highlighter.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "The field 'name' is mandatory")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "The field 'description' is mandatory")
    @Lob
    private String description;

    @Column
    @Lob
    private String instructions;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    private List<Message> messages;

    @ManyToOne
    @JoinColumn(name = "idOwner")
    @ToString.Exclude
    private ApplicationUser owner;

    @ManyToMany
    @JoinTable(
            name = "projectUsers",
            joinColumns = @JoinColumn(name = "idProject"),
            inverseJoinColumns = @JoinColumn(name = "idUser")
    )
    @ToString.Exclude
    private Set<ApplicationUser> applicationUsers;

    public int getNumberOfMessages () {return messages.size(); }
}
