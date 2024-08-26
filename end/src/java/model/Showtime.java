/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author ADMIN
 */

public class Showtime {
    private int id;
    private int movieId;
    private int theaterId;
    private Date showDate;
    private LocalDateTime startTime;

    // Constructors, getters, and setters

    public Showtime() {
    }

    public Showtime(int id, int movieId, int theaterId, Date showDate, LocalDateTime startTime) {
        this.id = id;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.showDate = showDate;
        this.startTime = startTime;
    }

    
    // Getters
    public int getId() {
        return id;
    }
  
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }
    
    @Override
    public String toString() {
        return "Showtime{" + "id=" + id + ", movieId=" + movieId + ", theaterId=" + theaterId + ", startTime=" + startTime + '}';
    }
    
    
}
