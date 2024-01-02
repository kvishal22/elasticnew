package com.kanna.elastic.service.impl;

import com.kanna.elastic.entity.Hethai;
import com.kanna.elastic.repo.HethaiRepo;
import com.kanna.elastic.service.HethaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HethaiServiceImpl implements HethaiService {

    @Autowired
    private HethaiRepo hethaiRepo;

    @Override
    public Iterable<Hethai> getHethai(){
        return hethaiRepo.findAll();
    }
    @Override
    public Hethai updatePeople(Hethai hethai,int id){
        Hethai hethai1 = hethaiRepo.findById(id).get();
        hethai1.setPeopleVisited(hethai.getPeopleVisited());
        return hethai;
    }
    @Override
    public Hethai save(Hethai hethai){
        return hethaiRepo.save(hethai);
    }
    @Override
    public void delete(int id){
         hethaiRepo.deleteById(id);
    }


}
