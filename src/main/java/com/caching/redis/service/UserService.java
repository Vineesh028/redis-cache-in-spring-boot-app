package com.caching.redis.service;

import java.util.List;

import com.caching.redis.model.User;

public interface UserService {

	User createUser(User user);

	List<User> getAll();

	User getUserById(int id);

	User updateUser(int id, User user);

	void deleteUser(int id);

	void deleteAllUsers();

}