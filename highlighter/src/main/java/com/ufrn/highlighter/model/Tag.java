package com.ufrn.highlighter.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tag implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "The field 'name' is mandatory")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "The field 'color' is mandatory")
    private String color;

    @Column
    private Character shortcut;

    @ManyToOne
    @JoinColumn(name="projectId")
    private Project project;
}
