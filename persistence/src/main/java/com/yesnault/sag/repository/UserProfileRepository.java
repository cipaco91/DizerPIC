package com.yesnault.sag.repository;

import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUser(User user);

}