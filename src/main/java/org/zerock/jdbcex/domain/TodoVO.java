// Source code is decompiled from a .class file using FernFlower decompiler.
package org.zerock.jdbcex.domain;

import lombok.Builder;

import java.time.LocalDate;


@Builder
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;


    public Long getTno() {
        return this.tno;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public String toString() {
        return "TodoVO(tno=" + this.getTno() + ", title=" + this.getTitle() + ", dueDate=" + this.getDueDate() + ", finished=" + this.isFinished() + ")";
    }

    public TodoVO(Long tno, String title, LocalDate dueDate, boolean finished) {
        this.tno = tno;
        this.title = title;
        this.dueDate = dueDate;
        this.finished = finished;
    }

    public TodoVO() {
    }
}
