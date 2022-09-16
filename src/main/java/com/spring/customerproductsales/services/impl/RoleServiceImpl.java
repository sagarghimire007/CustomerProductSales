package com.spring.customerproductsales.services.impl;

import com.spring.customerproductsales.model.Role;
import com.spring.customerproductsales.repository.RoleRepository;
import com.spring.customerproductsales.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
