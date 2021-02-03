package com.learning.redditclone.repository;

import java.util.Optional;

import com.learning.redditclone.model.Subreddit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
   Optional<Subreddit> findbyName(String subredditName);
}
