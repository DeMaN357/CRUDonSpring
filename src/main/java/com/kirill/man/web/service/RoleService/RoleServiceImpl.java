package com.kirill.man.web.service.RoleService;

import com.kirill.man.web.dao.RoleDao.RoleDao;
import com.kirill.man.web.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getRoles(String[] roles) {
        List<Role> roleList = new ArrayList<>();
        for (String role : roles) {
            if (role.equals("ADMIN") || role.equals("USER")) {
                roleList.add(roleDao.getRole(role));
            }
        }
        return roleList;
    }
}
