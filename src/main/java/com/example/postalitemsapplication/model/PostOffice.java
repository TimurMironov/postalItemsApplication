package com.example.postalitemsapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post_office")
public class PostOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "index")
    private String index;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToMany(mappedBy = "postOffices")
    private List<Item> items;

    public void addNewItem(Item item){
        if (items == null){
            items = new ArrayList<>();
        }
        items.add(item);
        item.getPostOffices().add(this);
    }
}
