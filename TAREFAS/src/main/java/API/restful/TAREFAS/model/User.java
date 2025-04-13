package API.restful.TAREFAS.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name = "tb_task_1")
public class User {

    @JsonProperty(access = Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameTask;

    private String descriptionTask;

    @JsonProperty(access = Access.READ_ONLY)
    private Date datainclusao;


    private boolean done;


    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }


    public Date getDatainclusao() {
        return datainclusao;
    }

    public void setDatainclusao(Date datainclusao) {
        this.datainclusao = new Date();
    }
}
