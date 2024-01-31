package com.example.postalitemsapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_of_recipient")
    private String nameOfRecipient;

    @Column(name = "type_of_item")
    @Enumerated(EnumType.STRING)
    private TypeOfItem typeOfItem;

    @Column(name = "index_of_recipient")
    private String indexOfRecipient;

    @Column(name = "address_of_recipient")
    private String addressOfRecipient;

    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(name = "item_postoffice"
            , joinColumns = @JoinColumn(name = "item_id")
            , inverseJoinColumns = @JoinColumn(name = "post_office_id"))
    private List<PostOffice> postOffices;
}
