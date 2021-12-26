package io.example.service.impl;

import io.example.model.domain.Role;
import io.example.repository.RoleRepository;
import io.example.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    
    private final RoleRepository roleRepo;

    @Transactional
    @Override
    public void create(String name) {
        roleRepo.findByAuthority(name).ifPresent(o -> {
            throw new IllegalStateException(String.format("Role with name - %s, already exists", name));
        });
        roleRepo.save(new Role(null, name));
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByAuthority(name)
            .orElseThrow(() -> new IllegalStateException(String.format("Role with name - %s, not found", name)));
    }

}