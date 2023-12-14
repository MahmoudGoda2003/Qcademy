package com.example.backend.admin;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.admin.service.AdminService;
import com.example.backend.person.model.Role;
import com.example.backend.promotion.dto.PromotionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:3000")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("changeRole")
    public ResponseEntity<String> changeRole(@RequestBody ChangeRoleDTO changeRoleDTO) throws Exception {
        return adminService.changePersonRole(changeRoleDTO.getUserId(), changeRoleDTO.isStatus());
    }

    @GetMapping("promotionRequests")
    public ResponseEntity<List<PromotionDTO>> getPromotionRequests() throws Exception {
        return adminService.getPromotionRequests();
    }

    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.ADMIN.name();
    }
}
