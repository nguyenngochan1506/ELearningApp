package vn.edu.hcmuaf.e_learningapp.features.module;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.edu.hcmuaf.e_learningapp.features.lesson.Lesson;

@Getter
@Setter
public class Module implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Integer number;
    private List<Lesson> lessons;

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", lessons=" + lessons +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
