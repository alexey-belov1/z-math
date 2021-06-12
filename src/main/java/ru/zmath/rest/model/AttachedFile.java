package ru.zmath.rest.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "attached_file")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AttachedFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "extension")
    private String extension;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
