package service.impl.extend;

import dao.impl.RoleDaoImpl;
import entity.Role;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class RoleList extends AbstractListOfNames<Integer, Role> {

    private final RoleDaoImpl roleDao = new RoleDaoImpl();
    private HashMap<Integer, Role> roles;

    public RoleList() {
        roles = roleDao.getAllRoles();
        this.setHashMap(roles);
    }
}
