package com.repo;

import com.model.user.UserInfo;
import com.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUser(User user);
    @Query("SELECT ui FROM UserInfo ui WHERE ui.user.id = (SELECT a.candidate.id FROM Application a WHERE a.id = :appId)")
    UserInfo findUserInfoByApplicationId(@Param("appId") Long appId);
}
