package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service.impl;

import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.model.Role;
import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.repository.RoleRepository;
import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service.RoleService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll(Sort.by("name"));
    }

}
