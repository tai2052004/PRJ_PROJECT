/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Movie {
    private int id;
    private String title;
    private int durationMinutes;
    private String imageUrl;
    private String director;
    private String description;
    private String starring;

    // Constructors, getters, and setters
    public Movie(int id, String title, int durationMinutes, String imageUrl, String director, String description, String starring) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.imageUrl = imageUrl;
        this.director = director;
        this.description = description;
        this.starring = starring;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDirector() {
        return director;
    }

    public String getDescription() {
        return description;
    }

    public String getStarring() {
        return starring;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", durationMinutes=" + durationMinutes + ", imageUrl=" + imageUrl + ", director=" + director + ", description=" + description + ", starring=" + starring + '}';
    }
    
    
}
