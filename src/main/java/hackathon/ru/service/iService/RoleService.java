package hackathon.ru.service.roleService;

import hackathon.ru.dto.RoleDto;
import hackathon.ru.model.Role;

import java.util.List;


public interface RoleService {
    public Role getRoleById(Long id);
    public List<Role> getAllRoles();
    public Role createRole(RoleDto roleDto);
    public Role updateRole(Long id, RoleDto roleDto);
    public void deleteRole(Long id);
}
