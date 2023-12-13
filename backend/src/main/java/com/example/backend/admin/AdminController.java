package com.example.backend.admin;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.admin.service.AdminService;
import com.example.backend.person.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("changeRole")
    public ResponseEntity<String> changeRole(@RequestBody String userId) throws Exception {
        return adminService.changePersonRole(Long.parseLong(userId));
    }

    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.ADMIN.name();
    }
}
