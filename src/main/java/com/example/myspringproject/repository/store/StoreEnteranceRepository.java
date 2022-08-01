package com.example.myspringproject.repository.store;

import com.example.myspringproject.entity.store.StoreEnterances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreEnteranceRepository extends JpaRepository<StoreEnterances, Long> {
}
