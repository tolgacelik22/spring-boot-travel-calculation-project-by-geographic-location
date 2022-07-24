package com.example.myspringproject.repo;

import com.example.myspringproject.entity.UserTravels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTravelsRepository extends JpaRepository<UserTravels, Long> {
}
