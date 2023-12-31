package com.caching.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caching.redis.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}