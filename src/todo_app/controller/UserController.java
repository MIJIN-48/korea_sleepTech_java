package todo_app.controller;

import java.util.List;

import todo_app.dto.request.UserSignInRequestDto;
import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.UserResponseDto;
import todo_app.service.UserServiceImpl;

public class UserController {
	private UserServiceImpl userServiceImpl;
	
	public UserController() {
		this.userServiceImpl = new UserServiceImpl();
	}
	
	public void signUpUser(UserSignUpRequestDto dto) {
		userServiceImpl.signUp(dto);
	}
	
	public void login(UserSignInRequestDto dto) {
		userServiceImpl.login(dto);
	}
	
	public void logout() {
		userServiceImpl.logout();
	}
	
	public boolean loginCheck() {
		return userServiceImpl.chekedLogin();
	}
	
	public List<UserResponseDto> findAllUser() {
		return userServiceImpl.findAll();
	}
	
	public UserResponseDto findByUserId(String userId) {
		return userServiceImpl.findByUserId(userId);
	}
	
	public void deleteUser(String userId) {
		userServiceImpl.delete(userId);
	}
	
	public String getLoggedId() {
		return userServiceImpl.getLoggedId();
	}
}
