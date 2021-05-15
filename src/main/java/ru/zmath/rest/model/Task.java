package ru.zmath.rest.model;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "task")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NonNull
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String file;
    private String comment;
    private Calendar deadline;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private double cost;

    private Calendar created;

    private String contact;
    private String cause;

    private boolean hidden;
}
