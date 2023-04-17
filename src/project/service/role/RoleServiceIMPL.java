package project.service.role;

import project.model.Role;
import project.model.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService{
    public static List<Role> roleList = new ArrayList<>();
    static {
        roleList.add(new Role(1, RoleName.ADMIN));
        roleList.add(new Role(2, RoleName.PM));
        roleList.add(new Role(3, RoleName.USER));
    }
    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public Role findByName(RoleName name) {
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getName() == name) {
                return roleList.get(i);
            }
        }
        return null;
    }
}
