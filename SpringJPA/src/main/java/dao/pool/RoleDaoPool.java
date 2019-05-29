package dao.pool;

import model.impl.Role;

import java.util.HashMap;

public interface RoleDaoPool {

    HashMap<Integer, Role> getAllRoles();
}
