package org.example.repository.dao;

import org.example.model.Role;
import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleRepositoryDao {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleRepositoryDao (RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public void add (Role role){
        roleRepository.save(role);
    }

    public void update(Role role){
        roleRepository.save(role);
    }

    public void delete(Long id){
        roleRepository.deleteById(id);
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }

    public Optional<Role> findById(Long id) {
        return Optional.ofNullable(roleRepository.getReferenceById(id));
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public boolean  existsById (Long id){ return roleRepository.existsById(id);}
}
