package com.example.postalitemsapplication.service;

import com.example.postalitemsapplication.exception.NotFoundException;
import com.example.postalitemsapplication.model.Item;
import com.example.postalitemsapplication.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements CommonServiceMethods<Item> {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> list() {
        return itemRepository.findAll();
    }

    @Override
    public Item getByID(Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Не удалось найти почтовое отправление с id " + id));
    }


    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void delete(Integer id) {
        itemRepository.deleteById(id);
    }
}
