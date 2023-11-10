package com.gomobile.integratedService.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection =  "user")
public class User {
    @Id
    private String id;
    private String uid;
    @Indexed(unique = true)
    private String email;
    // This need to be Double!
    private Integer credit;
    private List<Order> orders;

    public User(String uid, String email, Integer credit) {
        this.uid = uid;
        this.email = email;
        this.credit = credit;
        this.orders = new ArrayList<Order>();
    }
    public void addOrder(Order order) {
        orders.add(order);
    }

//    public Order getOrderById(String orderId){
//        Order result = null;
//        for(int i = 0; i <this.orders.size();i++){
//            if (this.orders.get(i).getId().equals(orderId)){
//                result = this.orders.get(i);
//            }
//        }
//        return result;
//    }
}
