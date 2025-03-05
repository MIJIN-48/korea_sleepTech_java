package todo_app.service;

import java.util.List;

import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.request.TaskUpdateRequestDto;
import todo_app.dto.response.TaskResponseDto;

public interface TaskService {
	void createTask(TaskRequestDto dto);
	List<TaskResponseDto> findByTodoDate(String userId, String todoDate);
	List<TaskResponseDto> findAllTask(String userId);
	void checkTodo(Long id);
	void updateTask(Long id, TaskUpdateRequestDto dto);
	void deleteTask(Long id);
	List<TaskResponseDto> findIsTodoCheck(String userId, String isTodo);
}
