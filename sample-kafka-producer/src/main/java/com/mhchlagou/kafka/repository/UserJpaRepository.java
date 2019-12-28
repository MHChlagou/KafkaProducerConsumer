package com.mhchlagou.kafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.mhchlagou.kafka.model.Producer;

@Component
public interface UserJpaRepository extends JpaRepository<Producer, Long>{

		Producer findByName(String name);
}
