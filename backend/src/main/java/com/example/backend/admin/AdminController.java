package com.example.backend.admin;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.admin.service.AdminService;
import com.example.backend.promotion.dto.PromotionDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("changeRole")
    public ResponseEntity<String> changeRole(@RequestBody ChangeRoleDTO changeRoleDTO) {
        return adminService.changePersonRole(changeRoleDTO.getUserId(), changeRoleDTO.isStatus());
    }

    @GetMapping("promotionRequests")
    public ResponseEntity<List<PromotionDTO>> getPromotionRequests() {
        return adminService.getPromotionRequests();
    }

}
