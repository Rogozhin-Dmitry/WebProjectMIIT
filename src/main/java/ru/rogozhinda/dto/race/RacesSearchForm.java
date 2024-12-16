package ru.rogozhinda.dto.race;

public class RacesSearchForm {
    public String searchTerm;

    public RacesSearchForm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public RacesSearchForm() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
