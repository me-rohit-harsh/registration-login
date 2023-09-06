package com.indianbank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.indianbank.entity.User;
import com.indianbank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;


	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> allUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean deleteUser(Long id) {
		User user = userRepository.findById(id).get();
		if (user != null) {
			userRepository.delete(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Long saveUserId(User user) {
		User user2 = userRepository.save(user);
		return user2.getId();
	}

}
