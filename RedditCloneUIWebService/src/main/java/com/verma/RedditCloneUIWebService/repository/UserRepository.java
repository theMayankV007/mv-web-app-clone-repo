package com.verma.RedditCloneUIWebService.repository;

import com.verma.RedditCloneUIWebService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
