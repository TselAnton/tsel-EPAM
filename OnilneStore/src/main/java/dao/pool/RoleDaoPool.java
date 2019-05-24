package dao.pool;

import model.Role;

import java.util.HashMap;

public interface RoleDaoPool {

    HashMap<Integer, Role> getAllRoles();
}
