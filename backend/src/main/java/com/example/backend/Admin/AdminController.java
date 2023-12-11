package com.example.backend.admin;

import com.example.backend.person.model.Role;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping("test")
    public String test() throws Exception {
        return "hello world from " + Role.ADMIN.name();
    }
}
