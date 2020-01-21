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
public class Message implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String tag;

    @Column(nullable = false)
    @NotNull(message = "The field 'body' is mandatory" )
    @Lob
    private String body;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
