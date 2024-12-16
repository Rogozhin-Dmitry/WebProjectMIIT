package ru.rogozhinda.dto.driver;

public class DriversSearchForm {
    public String searchTerm;

    public DriversSearchForm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public DriversSearchForm() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
