package todo_app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import todo_app.entity.User;

/*
 * User 데이터에 대한 List 컬렉션 저장소 정의
 * - 사용자 등록(save)
 * - 사용자 단건 조회(findById)
 * - 사용자 전체 조회(findAll)
 * - 사용자 삭제(delete)
 */

public class UserRepository {
	List<User> users = new ArrayList<>();
	private static final UserRepository instance = new UserRepository();
	
	private UserRepository() {}
	
	public static UserRepository getInstance() {
		return instance;
	}
	
	public void save(User user) {
		users.add(user);
	}
	
	public Optional<User> findByUserId(String userId) {
		return users.stream()
				.filter(user -> user.getUserId().equals(userId))
				.findFirst();
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public void delete(User user) {
		users.remove(user);
	}
	
}
