package com.caching.redis.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.caching.redis.entity.UserEntity;
import com.caching.redis.model.User;
import com.caching.redis.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository repository;

	@Override
	public User createUser(User user) {
		UserEntity entity = modelMapper.map(user, UserEntity.class);
		repository.save(entity);
		return modelMapper.map(entity, User.class);
	}

	@Override
	@Cacheable("users")
	public List<User> getAll() {
		 doLongRunningTask();

		return repository.findAll().stream().map(e -> modelMapper.map(e, User.class)).collect(Collectors.toList());
	}

	@Override
	@Cacheable("user")
	public User getUserById(int id) {
		 doLongRunningTask();

		return modelMapper.map(repository.findById(id), User.class);
	}

	@Override
	@CachePut(value="user", key="#id")
	public User updateUser(int id, User user) {
		UserEntity existingUser = repository.findById(id).get();
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		return modelMapper.map(repository.save(existingUser), User.class);

	}

	@Override
	@CacheEvict(value = "user", key = "#id")
	public void deleteUser(int id) {

		repository.deleteById(id);

	}

	@Override
	@CacheEvict(value = { "user", "users" }, allEntries = true)
	public void deleteAllUsers() {
		repository.deleteAll();
	}
	
	private void doLongRunningTask() {
	    try {
	      Thread.sleep(3000);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
}