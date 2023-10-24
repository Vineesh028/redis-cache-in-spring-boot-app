package com.caching.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caching.redis.model.User;
import com.caching.redis.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getAll() {
		return userService.getAll();
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}


	@PutMapping("/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public String  deleteUser(@PathVariable int id) {
		 userService.deleteUser(id);
		 return "";
	}

	@DeleteMapping
	public String deleteAllUsers() {
		 userService.deleteAllUsers();
		 return "";
	}

}