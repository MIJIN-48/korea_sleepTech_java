package todo_app.controller;

import java.util.List;

import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.request.TaskUpdateRequestDto;
import todo_app.dto.response.TaskResponseDto;
import todo_app.service.TaskServiceImpl;

public class TaskController {
	private TaskServiceImpl taskServiceImpl;
	
	public TaskController() {
		this.taskServiceImpl = new TaskServiceImpl();
	}
	
	public void createTask(TaskRequestDto dto) {
		taskServiceImpl.createTask(dto);
	}
	
	public List<TaskResponseDto> findTaskByTodoDate(String userId, String todoDate) {
		return taskServiceImpl.findByTodoDate(userId, todoDate);
	}
	
	public List<TaskResponseDto> findTaskByUserId(String userId) {
		return taskServiceImpl.findAllTask(userId);
	}
	
	public void checkTodo(String userId, Long id) {
		taskServiceImpl.checkTodo(userId, id);
	}
	
	public void updateTask(String userId, Long id, TaskUpdateRequestDto dto) {
		taskServiceImpl.updateTask(userId, id, dto);
	}
	
	public void deleteTask(String userId, Long id) {
		taskServiceImpl.deleteTask(userId, id);
	}
	
	public List<TaskResponseDto> findIsTodo(String userId, int isTodo) {
		return taskServiceImpl.findIsTodoCheck(userId, isTodo);
	}
}
