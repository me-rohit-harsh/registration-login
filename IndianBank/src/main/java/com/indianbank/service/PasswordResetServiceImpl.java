package com.indianbank.service;

import org.springframework.stereotype.Service;

import com.indianbank.entity.PasswordReset;
import com.indianbank.repository.PasswordResetRepository;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

	private PasswordResetRepository passwordResetRepository;

	public PasswordResetServiceImpl(PasswordResetRepository passwordResetRepository) {
		this.passwordResetRepository = passwordResetRepository;
	}

	
	@Override
	public PasswordReset saveUser(PasswordReset passwordReset) {

		return passwordResetRepository.save(passwordReset);
	}

}
