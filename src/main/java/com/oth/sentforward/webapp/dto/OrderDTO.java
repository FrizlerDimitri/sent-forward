package com.oth.sentforward.webapp.dto;

import com.oth.sentforward.Utils.Pair;
import de.oth.homeDente.entity.OrderedDish;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

public class OrderDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String details;

    private List<OrderedDish> orders = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(Date date, String details) {

        this.date = date;

        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public List<OrderedDish> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderedDish> orders) {
        this.orders = orders;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
