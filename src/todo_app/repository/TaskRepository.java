package todo_app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import todo_app.entity.Task;

/*
 * ask 데이터에 대한 List 컬렉션 저장소 정의
 * - 할일 등록(save)
 * - 할일 단건 조회 (로그인된 아이디의 할일 리스트)
 * - 할일 전체 조회 (모든 회원의 리스트)
 * - 할일 수정
 * - 할일 삭제
 */

public class TaskRepository {
	List<Task> tasks = new ArrayList<>();
	private static final TaskRepository instance = new TaskRepository();
	
	private TaskRepository() {}
	
	public static TaskRepository getInstance() {
		return instance;
	}
	
	public void save(Task task) {
		tasks.add(task);
	}
	
//	public Optional<Task> findTaskByUserId(String userId) {
//		return tasks.stream()
//				.filter(task -> task.getUserId().equals(userId))
//				.findFirst();
//	}
	
	public Optional<Task> findTaskById(Long id) {
		return tasks.stream()
				.filter(task -> task.getId().equals(id))
				.findFirst();
	}
	
	public List<Task> findAll() {
		return tasks;
	}
	
	public void delete(Task task) {
		tasks.remove(task);
	}
}
