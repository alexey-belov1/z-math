package ru.zmath.back.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private String comment;

    private Calendar deadline;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    //TODO поменять на int
    private Double cost;

    private Double preparedCost;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private Calendar created;

    private String contact;

    private String cause;

    private boolean archived;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttachedFile> attachedFiles = new ArrayList<>();
}
