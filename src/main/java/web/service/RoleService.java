package web.service;

import org.springframework.stereotype.Service;
import web.model.Role;

public interface RoleService {
    Role getRoleByName(String nameRole);
}
