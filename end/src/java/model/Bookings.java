package model;

import java.io.Serializable;
import java.util.Date;


public class Bookings implements Serializable {
    

    private int id;
    private int  user_id;
    private int showtime_id;
    private String seat_id;
    private String status;
    private String payment_method;
    private String booking_date;
    private String price;

    public Bookings(int user_id, int showtime_id, String seat_id, String status, String payment_method, String booking_date, String price) {
        this.id = 0;
        this.user_id = user_id;
        this.showtime_id = showtime_id;
        this.seat_id = seat_id;
        this.status = status;
        this.payment_method = payment_method;
        this.booking_date = booking_date;
        this.price = price;
    }
        public Bookings() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getShowtime_id() {
        return showtime_id;
    }

    public void setShowtime_id(int showtime_id) {
        this.showtime_id = showtime_id;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void String(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

  
    
    
    
}
