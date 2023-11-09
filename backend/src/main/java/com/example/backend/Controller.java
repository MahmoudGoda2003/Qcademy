package com.example.backend;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {})
@RestController
public class Controller {

    @GetMapping("/SignUp")
    public String SignUp(){
        System.out.println("my name is goda");
        return "name";
    }

}
