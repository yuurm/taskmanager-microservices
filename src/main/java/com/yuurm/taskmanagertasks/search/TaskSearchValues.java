package com.yuurm.taskmanagertasks.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchValues {


    private String title;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;
    private Long userId;

    private Date dateFrom; // для задания периода по датам
    private Date dateTo;

    // постраничность
    private Integer pageNumber;
    private Integer pageSize;

    // сортировка
    private String sortColumn;
    private String sortDirection;

    // такие же названия должны быть у объекта на frontend

}
