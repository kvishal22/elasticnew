package com.kanna.elastic.controller;

import com.kanna.elastic.entity.Hethai;
import com.kanna.elastic.service.HethaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HethaiController {

    @Autowired
    private HethaiService hethaiService;

    @GetMapping("/findAll")
    public Iterable<Hethai> getAll(){
        return hethaiService.getHethai();
    }
 @PostMapping("/addHethai")
    public Hethai save(@RequestBody Hethai hethai){
        return hethaiService.save(hethai);
    }


}
