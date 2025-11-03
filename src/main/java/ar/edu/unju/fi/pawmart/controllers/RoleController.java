package ar.edu.unju.fi.pawmart.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.pawmart.dtos.RoleDto;
import ar.edu.unju.fi.pawmart.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // GET ALL
    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    // CREATE
    @PostMapping
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public RoleDto updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return roleService.update(id, roleDto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.delete(id);
    }
}
