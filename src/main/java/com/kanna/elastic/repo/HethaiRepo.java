package com.kanna.elastic.repo;

import com.kanna.elastic.entity.Hethai;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface HethaiRepo extends ElasticsearchRepository<Hethai, Integer> {
}
