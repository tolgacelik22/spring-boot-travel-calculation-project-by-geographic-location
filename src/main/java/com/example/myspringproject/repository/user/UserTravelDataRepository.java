package com.example.myspringproject.repository.user;

import com.example.myspringproject.entity.user.UserTravelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserTravelDataRepository extends JpaRepository<UserTravelData, Long> {
    @Query("select u from UserTravelData u where u.courierId = ?1")
    UserTravelData findByCourierId(int courierId);

    @Modifying
    @Query("update UserTravelData u set u.totalTravel = ?1 where u.courierId = ?2")
    void setTotalTravelByCourierId(Long totalTravel, int courierId);
}
