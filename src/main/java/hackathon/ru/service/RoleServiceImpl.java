package hackathon.ru.service;

import hackathon.ru.dto.RoleDto;
import hackathon.ru.exception.custom.RoleNotFoundException;
import hackathon.ru.model.Role;
import hackathon.ru.repository.RoleRepository;
import hackathon.ru.service.iService.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("role with this id is not found"));
    }

    @Override
    public List<Role> getAllRoles() {
        return new ArrayList<>(roleRepository.findAll());
    }

    @Override
    public Role createRole(RoleDto roleDto) {
        Role role = Role.builder()
                .name(roleDto.getName())
                .build();
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, RoleDto roleDto) {
        Role role = getRoleById(id);
        role.setName(role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        Role role = getRoleById(id);
        roleRepository.delete(role);
    }
}
