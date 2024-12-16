package ru.rogozhinda.dto.result;

public class ResultViewModel {
    private String id;
    private Integer position;
    private Integer time;

    public ResultViewModel(String id, Integer position, Integer time) {
        this.id = id;
        this.position = position;
        this.time = time;
    }

    public ResultViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
