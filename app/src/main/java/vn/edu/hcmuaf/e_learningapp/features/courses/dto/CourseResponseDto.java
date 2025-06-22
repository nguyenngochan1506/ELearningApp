package vn.edu.hcmuaf.e_learningapp.features.courses.dto;

import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.categories.dto.CategoryResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.module.dto.ModuleResponseDto;
import vn.edu.hcmuaf.e_learningapp.features.user.dtos.UserResponse;

public class CourseResponseDto {
    private int id;
    private String name;
    private String description;
    private Double price;
    private String thumbnail;
    private CategoryResponseDto category;
    private UserResponse teacher;
    private boolean isPublished;
    private List<ModuleResponseDto> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CategoryResponseDto getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDto category) {
        this.category = category;
    }

    public UserResponse getTeacher() {
        return teacher;
    }

    public void setTeacher(UserResponse teacher) {
        this.teacher = teacher;
    }

    public boolean getPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public List<ModuleResponseDto> getModules() {
        return modules;
    }

    public void setModules(List<ModuleResponseDto> modules) {
        this.modules = modules;
    }
}
