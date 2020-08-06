package com.kirill.man.web.service.RoleService;

import com.kirill.man.web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getRoles(String [] roles);
}
