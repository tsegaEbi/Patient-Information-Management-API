package com.therapy.therapy.finance.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService {
    @Autowired
    private ItemRepository repository;

    @Override
    public Item get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Item> list(Pageable pageable) {
        return null;
    }

    @Override
    public Item add(Item item) throws Exception {
        return null;
    }

    @Override
    public Item update(Item item) throws Exception {
        return null;
    }

    @Override
    public void delete(Item item) throws Exception {

    }
}
