package com.example.myspringproject.repo;

import com.example.myspringproject.entity.StoreEnterances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreEnterancesRepository extends JpaRepository<StoreEnterances, Long> {
}
