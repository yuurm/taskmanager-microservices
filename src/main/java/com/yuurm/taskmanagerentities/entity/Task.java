package com.yuurm.taskmanagerentities.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;



@Entity
@Table(name = "task", schema = "todo", catalog = "taskmanager_todo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Task implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean completed;

    @Column(name = "task_date")
    private Date taskDate;


    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

   @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;



    @Column(name="user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title;
    }
}
