package vn.edu.hcmuaf.e_learningapp.features.module.dto;

import java.io.Serializable;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.lesson.dto.LessonResponseDto;

public class ModuleResponseDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Integer number;
    private List<LessonResponseDto> lessons;

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

    public List<LessonResponseDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonResponseDto> lessons) {
        this.lessons = lessons;
    }
}
