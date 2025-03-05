package todo_app.service;

import java.util.List;

import todo_app.dto.request.UserSignInRequestDto;
import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.UserResponseDto;

public interface UserService {
	void signUp(UserSignUpRequestDto dto);
	void login(UserSignInRequestDto dto);
	void logout();
	boolean chekedLogin();
	List<UserResponseDto> findAll();
	UserResponseDto findByUserId(String userId);
	void delete(String userId);
}
