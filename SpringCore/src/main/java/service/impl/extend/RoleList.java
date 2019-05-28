package service.impl.extend;

import dao.pool.impl.RoleDaoPoolImpl;
import model.impl.Role;
import org.springframework.beans.factory.annotation.Autowired;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class RoleList extends AbstractListOfNames<Integer, Role> {

    @Autowired
    private RoleDaoPoolImpl roleDao;
    private HashMap<Integer, Role> roles;

    public RoleList() {
    }

    public void initialize() {
        roles = roleDao.getAllRoles();
        this.setHashMap(roles);
    }
}
