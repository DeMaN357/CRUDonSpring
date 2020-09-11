package com.kirill.man.web.service.RoleService;

import com.kirill.man.web.dao.RoleDao.RoleDao;
import com.kirill.man.web.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRoles(String[] roles) {
        Set<Role> roleList = new HashSet<>();
        for (Role roleDao : getAllRoles()) {
            if (Arrays.stream(roles).anyMatch(role->role.equals(roleDao.getRole()))) {
                roleList.add(roleDao);
            }
        }
        return roleList;
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
