package com.mfpardini.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mfpardini.cursomc.domain.Cliente;
import com.mfpardini.cursomc.repositories.ClienteRepository;
import com.mfpardini.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cli = clienteRepository.findByEmail(email);
		if (cli == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPassword = newPassword();
		cli.setSenha(pe.encode(newPassword));

		emailService.sendNewPasswordEmail(cli, newPassword);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // generates a digit
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // generates upper case letter
			return (char) (rand.nextInt(26) + 65);
		} else { // generates lower case letter
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
