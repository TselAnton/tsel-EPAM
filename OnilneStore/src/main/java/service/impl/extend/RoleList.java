package service.impl.extend;

import dao.pool.impl.RoleDaoPoolImpl;
import model.Role;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class RoleList extends AbstractListOfNames<Integer, Role> {

    private final RoleDaoPoolImpl roleDao = new RoleDaoPoolImpl();
    private HashMap<Integer, Role> roles;

    public RoleList() {
        roles = roleDao.getAllRoles();
        this.setHashMap(roles);
    }
}
