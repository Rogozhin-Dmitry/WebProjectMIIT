package ru.rogozhinda.dto.team;

public class TeamsSearchForm {
    public String searchTerm;

    public TeamsSearchForm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public TeamsSearchForm() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
