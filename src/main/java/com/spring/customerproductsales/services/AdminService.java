package com.spring.customerproductsales.services;

import com.spring.customerproductsales.dto.AdminDto;
import com.spring.customerproductsales.model.Admin;

public interface AdminService {

    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);
}
