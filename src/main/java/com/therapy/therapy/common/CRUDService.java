package com.therapy.therapy.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService<T extends Model> {
    T get(Long id);

    Page<T> list(Pageable pageable);

    T add(T t) throws Exception;

    T update(T t) throws Exception;

    void delete(T t) throws Exception;
}
