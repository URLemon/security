package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

@Repository
public interface RoleDao {
    Role getRoleByName(String nameRole);
}
