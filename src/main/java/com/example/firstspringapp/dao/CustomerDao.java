package com.example.firstspringapp.dao;

import com.example.firstspringapp.dto.SupportDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToMany(mappedBy = "customerDao", cascade = CascadeType.ALL)
    private List<NoteDao> customerNotes = new ArrayList<>();

    @OneToMany(mappedBy = "orderDao", cascade = CascadeType.ALL)
    private List<SupportDao> customerOrders = new ArrayList<>();

    public void createNote(NoteDao note) {
        note.setCustomerDao(this);
        customerNotes.add(note);
    }

    public void createOrder(SupportDao order) {
        order.setOrderDao(this);
        customerOrders.add(order);
    }
}
