package com.ufrn.highlighter.model;


import com.opencsv.bean.CsvBindByPosition;
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
    @CsvBindByPosition(position = 0)
    private Long id;

    @Column
    @CsvBindByPosition(position = 2)
    private String tag;

    @Column(nullable = false)
    @NotNull(message = "The field 'body' is mandatory" )
    @Lob
    @CsvBindByPosition(position = 1)
    private String body;

    @ManyToOne
    @JoinColumn(name = "projectId")
    @ToString.Exclude
    private Project project;
}
