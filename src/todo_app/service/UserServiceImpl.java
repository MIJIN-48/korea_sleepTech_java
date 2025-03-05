package todo_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import todo_app.dto.request.UserSignInRequestDto;
import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.UserResponseDto;
import todo_app.entity.User;
import todo_app.repository.UserRepository;

public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private User loggedUser;
	private static long userSequenceId = 1;
	
	public UserServiceImpl() {
		this.repository = UserRepository.getInstance();
	}
	
	private Long generatedUserId() {
		return userSequenceId++;
	}

	@Override
	public void signUp(UserSignUpRequestDto dto) {
		try {
			Optional<User> checkedUserId = repository.findByUserId(dto.getUserId()); 
			
			if (checkedUserId.isPresent()) {
				System.out.println("해당 ID가 이미 존재합니다. 다른 아이디를 사용해 주세요. ID: " + dto.getUserId());
			} else if (!dto.getPassword().equals(dto.getPasswordCheck())) {
				System.out.println("비밀번호가 서로 다릅니다. 다시 확인해 주세요.");
			} else {
				User newUser = new User(generatedUserId(), dto.getUserId(), dto.getPassword(), dto.getName());
				repository.save(newUser);
				System.out.println("회원가입이 완료되었습니다.");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void login(UserSignInRequestDto dto) {
		repository.findByUserId(dto.getUserId()).ifPresentOrElse(user -> {
			if (user.getPassword().equals(dto.getPassword())) {
				loggedUser = user;
				System.out.println(user.getName() + "님 반갑습니다.");
			} else {
				System.out.println("비밀번호가 틀렸습니다. 다시 확인해 주세요.");
			}
		}, () -> System.out.println("해당 아이디가 존재하지 않습니다. 다시 확인해 주세요. ID: " + dto.getUserId()));
	}

	@Override
	public void logout() {
		if (loggedUser != null) {
			System.out.println(loggedUser.getName() + "님의 계정이 로그아웃 되었습니다.");
			loggedUser = null;
		} else {
			System.out.println("로그인 상태가 아닙니다. 로그아웃할 수 없습니다.");
		}
	}

	@Override
	public boolean chekedLogin() {
		if (loggedUser == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<UserResponseDto> findAll() {
		List<UserResponseDto> responseDtos = null;
		
		try {
			List<User> users = repository.findAll();
			
			responseDtos = users.stream()
					.map(user -> new UserResponseDto(user.getUserId(), user.getName()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.getMessage();
		}

		return responseDtos;
	}

	@Override
	public UserResponseDto findByUserId(String userId) {
		UserResponseDto responseDtos = null;
		
		try {
			User user = repository.findByUserId(userId)
					.orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을 수 없습니다. ID: " + userId));
			
			responseDtos = new UserResponseDto(user.getUserId(),user.getName());
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return responseDtos;
	}

	@Override
	public void delete(String userId) {
		try {
			User user = repository.findByUserId(userId)
					.orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을 수 없습니다. ID: " + userId));
			
			repository.delete(user);
			
		} catch (Exception e) {
			e.getMessage();
		}		
	}
	
	public String getLoggedId() {
		if (loggedUser == null) {
			return null;
		} else {
			return loggedUser.getUserId();
		}
	}
}
