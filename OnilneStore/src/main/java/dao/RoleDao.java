package dao;

import entity.Role;

import java.util.HashMap;

public interface RoleDao {

    HashMap<Integer, Role> getAllRoles();
}
