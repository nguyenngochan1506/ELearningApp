package vn.edu.hcmuaf.e_learningapp.features.lesson;

import java.io.Serializable;
import java.util.List;

import vn.edu.hcmuaf.e_learningapp.features.file.FileResponseDto;


public class Lesson implements Serializable {
    private int id;
    private String name;
    private String content;
    private Integer number;
    private Integer duration;
    private String videoUrl;
    private List<FileResponseDto> files;

    public Lesson(int id, String name, String content, Integer number, Integer duration, String videoUrl) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.number = number;
        this.duration = duration;
        this.videoUrl = videoUrl;
    }

    public Lesson() {
    }

    public int getId() {
        return id;
    }

    public List<FileResponseDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileResponseDto> files) {
        this.files = files;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", number=" + number +
                ", duration=" + duration +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
