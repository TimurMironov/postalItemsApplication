package com.example.postalitemsapplication.service;

import com.example.postalitemsapplication.exception.NotFoundException;
import com.example.postalitemsapplication.model.PostOffice;
import com.example.postalitemsapplication.repository.PostOfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostOfficeService implements CommonServiceMethods<PostOffice> {

    private final PostOfficeRepository postOfficeRepository;

    public PostOfficeService(PostOfficeRepository postOfficeRepository) {
        this.postOfficeRepository = postOfficeRepository;
    }

    @Override
    public List<PostOffice> list() {
        return postOfficeRepository.findAll();
    }

    @Override
    public PostOffice getByID(Integer id) {
        return postOfficeRepository.findById(id).orElseThrow(()-> new NotFoundException("Офис с id " + id + " не найден"));
    }


    @Override
    public PostOffice save(PostOffice postOffice) {
        return postOfficeRepository.save(postOffice);
    }

    @Override
    public void delete(Integer id) {
        postOfficeRepository.deleteById(id);

    }
}
