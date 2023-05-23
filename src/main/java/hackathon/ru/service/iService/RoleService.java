package hackathon.ru.service.iService;

import hackathon.ru.dto.RoleDto;
import hackathon.ru.model.Role;

import java.util.List;


public interface RoleService {
    Role getRoleById(Long id);

    List<Role> getAllRoles();

    Role createRole(RoleDto roleDto);

    Role updateRole(Long id, RoleDto roleDto);

    void deleteRoleById(Long id);
}
