package ru.zmath.rest.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "status")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NonNull
    private int id;
    private String name;

}
