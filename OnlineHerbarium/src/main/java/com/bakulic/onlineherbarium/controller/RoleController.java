package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Role;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateRoleDTO;
import com.bakulic.onlineherbarium.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    @GetMapping
    public String getRoleForm(Model model){
        CreateOrUpdateRoleDTO newRole = new CreateOrUpdateRoleDTO();
        model.addAttribute("role", newRole);
        return "createRole";
    }

    @PostMapping
    public String createRole(@ModelAttribute("role")  CreateOrUpdateRoleDTO createRoleDTO){
        roleService.createRole(createRoleDTO);
        return "redirect:role/all";
    }

    @GetMapping("update/{id}")
    public String getUpdateRoleForm(Model model, @PathVariable("id") int id){
        Role role = roleService.getRoleRepository().findById(id);
        model.addAttribute("role", role);
        return "updateRole";
    }

    @PostMapping("/update/{id}")
    public String UpdateRole(@PathVariable ("id") int id, @ModelAttribute("family") CreateOrUpdateRoleDTO updateRoleDTO){
        roleService.updateRole(id, updateRoleDTO);
        return "redirect:/role/all";
    }

    @GetMapping("/all")
    public String getFamilyList(Model model) {
        List<Role> list = roleService.getAllRoles();
        model.addAttribute("roles", list);
        return "roleList";
    }

    @GetMapping("/users")
    public String getUsersByRole(Model model, @PathVariable ("id") int id){
        Collection<User> list = roleService.getAllUsersByRole(id);
        model.addAttribute("users", list);
        return "roleUsers";
    }

}
