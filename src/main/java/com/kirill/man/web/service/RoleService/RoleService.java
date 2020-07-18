package com.kirill.man.web.service.RoleService;

import com.kirill.man.web.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles(String [] roles);
}
