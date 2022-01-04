package com.verma.RedditCloneUIWebService.repository;

import com.verma.RedditCloneUIWebService.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

}
