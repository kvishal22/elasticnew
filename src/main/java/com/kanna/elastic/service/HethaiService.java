package com.kanna.elastic.service;

import com.kanna.elastic.entity.Hethai;

public interface HethaiService {

    Iterable<Hethai> getHethai();

    Hethai updatePeople(Hethai hethai,int id);
    Hethai save(Hethai hethai);

    void delete(int id);
}
