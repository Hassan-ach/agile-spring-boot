// package com.ensa.agile.application.service;
//
// import java.util.List;
// import java.util.Optional;
//
// import org.springframework.web.bind.annotation.RequestBody;
//
// import com.ensa.agile.application.dto.user.CreateUserDto;
// import com.ensa.agile.application.dto.user.UpdateUserDto;
// import com.ensa.agile.application.dto.user.UserResponseDto;
// import com.ensa.agile.domain.entity.User;
// import com.ensa.agile.domain.exception.EmailAlreadyUsedExeption;
// import com.ensa.agile.domain.exception.UserNotFoundException;
// import com.ensa.agile.domain.repository.UserRepositry;
//
// import jakarta.validation.Valid;
//
// public class UserService {
// 	private final UserRepositry repo;
//
// 	public UserService(UserRepositry userRepositry) {
// 		this.repo = userRepositry;
// 	}
//
// 	public UserResponseDto add(@Valid @RequestBody CreateUserDto createUserDto) {
// 		// make sure the email is unique
// 		if (repo.existsByEmail(createUserDto.email)) {
// 			throw new EmailAlreadyUsedExeption(createUserDto.email);
// 		}
//
// 		User u = new User();
// 		u.setFirstName(createUserDto.firstName);
// 		u.setLastName(createUserDto.lastName);
// 		u.setEmail(createUserDto.email);
//
// 		// just for now, store the raw pass
// 		u.setPassword(createUserDto.password);
//
// 		u = repo.save(u);
//
// 		// build response DTO
// 		UserResponseDto resp = new UserResponseDto(u);
// 		resp.id = u.getId();
// 		resp.firstName = u.getFirstName();
// 		resp.lastName = u.getLastName();
// 		resp.email = u.getEmail();
//
// 		return resp;
// 	}
//
// 	public UserResponseDto update(@Valid @RequestBody UpdateUserDto updateUserDto) {
// 		// fetch the user to update
//
// 		User u = repo.findById(updateUserDto.id)
// 				.orElseThrow(() -> new UserNotFoundException(updateUserDto.id));
//
// 		// update fields if present
// 		Optional.ofNullable(updateUserDto.email).ifPresent(newEmail -> {
// 			// check if the new email is unique
// 			if (!newEmail.equals(u.getEmail()) && repo.existsByEmail(newEmail)) {
// 				throw new EmailAlreadyUsedExeption(newEmail);
// 			}
// 			u.setEmail(newEmail);
// 		});
// 		Optional.ofNullable(updateUserDto.firstName)
// 				.ifPresent(u::setFirstName);
// 		Optional.ofNullable(updateUserDto.lastName)
// 				.ifPresent(u::setLastName);
//
// 		// persist the updated user
// 		User updatedUser = repo.save(u);
//
// 		// return the response DTO
// 		return new UserResponseDto(updatedUser);
// 	}
//
// 	public void delete(Long id) {
// 		if (!repo.existsById(id)) {
// 			throw new UserNotFoundException(id);
// 		}
// 		repo.deleteById(id);
// 	}
//
// 	public User getById(Long id) {
// 		// fetch the user to update
// 		User u = repo.findById(id)
// 				.orElseThrow(() -> new UserNotFoundException(id));
//
// 		return u;
// 	}
//
// 	public List<User> getAll() {
// 		return repo.findAll();
// 	}
// }
