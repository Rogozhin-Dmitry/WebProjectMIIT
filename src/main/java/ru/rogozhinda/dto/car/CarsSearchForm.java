package ru.rogozhinda.dto.car;

public class CarsSearchForm {
    public String searchTerm;

    public CarsSearchForm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public CarsSearchForm() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
