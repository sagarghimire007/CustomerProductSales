package com.spring.customerproductsales.services;

import com.spring.customerproductsales.model.Role;

public interface RoleService {

    Role findByName(String name);
}
