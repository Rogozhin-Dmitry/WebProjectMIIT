package ru.rogozhinda.dto.result;

import ru.rogozhinda.dto.base.BaseViewModel;

public class ResultDetailsViewModel {
    private BaseViewModel base;
    private ResultViewModel result;
    private Integer laps;
    private Integer lapTime;
    private Integer gap;
    private Integer points;
    private String status;

    public ResultDetailsViewModel(BaseViewModel base, ResultViewModel result, Integer laps, Integer lapTime, Integer gap, Integer points, String status) {
        this.base = base;
        this.result = result;
        this.laps = laps;
        this.lapTime = lapTime;
        this.gap = gap;
        this.points = points;
        this.status = status;
    }

    public ResultDetailsViewModel() {
    }

    public BaseViewModel getBase() {
        return base;
    }

    public void setBase(BaseViewModel base) {
        this.base = base;
    }

    public ResultViewModel getResult() {
        return result;
    }

    public void setResult(ResultViewModel result) {
        this.result = result;
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public Integer getLapTime() {
        return lapTime;
    }

    public void setLapTime(Integer lapTime) {
        this.lapTime = lapTime;
    }

    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
