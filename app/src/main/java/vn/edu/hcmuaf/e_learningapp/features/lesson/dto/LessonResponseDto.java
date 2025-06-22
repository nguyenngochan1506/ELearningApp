package vn.edu.hcmuaf.e_learningapp.features.lesson.dto;

import java.io.Serializable;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.file.FileResponseDto;

public class LessonResponseDto implements Serializable {
    private Long id;
    private String name;
    private String content;
    private Integer number;
    private Integer duration;
    private List<FileResponseDto> files;
    private String videoUrl;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<FileResponseDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileResponseDto> files) {
        this.files = files;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
