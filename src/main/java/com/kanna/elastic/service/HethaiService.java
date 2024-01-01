package com.kanna.elastic.service;

import com.kanna.elastic.entity.Hethai;
import com.kanna.elastic.repo.HethaiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HethaiService {

    @Autowired
    private HethaiRepo hethaiRepo;


    public Iterable<Hethai> getHethai(){
        return hethaiRepo.findAll();
    }
    public Hethai updatePeople(Hethai hethai,int id){
        Hethai hethai1 = hethaiRepo.findById(id).get();
        hethai1.setPeopleVisited(hethai.getPeopleVisited());
        return hethai;
    }
    public Hethai save(Hethai hethai){
        return hethaiRepo.save(hethai);
    }
    public void delete(int id){
         hethaiRepo.deleteById(id);
    }
}
