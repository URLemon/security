package web.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao dao;

    public RoleServiceImpl(@Qualifier("roleDaoImpl") RoleDao dao){
        this.dao = dao;
    }

    @Override
    @Transactional
    public Role getRoleByName(String nameRole) {
        return dao.getRoleByName(nameRole);
    }
}
