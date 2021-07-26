package com.therapy.therapy.user.roles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImp(RoleRepository repository){
        this.repository =repository;
    }

    @Override
    public Role get(Long id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public List<Role> findAll( ) {
        return repository.findAll( );
    }
    @Override
    public Page<Role> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Role add(Role role) throws Exception {
        return repository.save(role);
    }

    @Override
    @Transactional
    public Role update(Role role) throws Exception {
        return add(role);
    }

    @Override
    @Transactional
    public void delete(Role role) throws Exception {
            repository.delete(role);
    }
}
