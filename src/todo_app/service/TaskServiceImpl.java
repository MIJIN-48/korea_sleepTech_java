package todo_app.service;

import java.util.List;
import java.util.stream.Collectors;

import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.request.TaskUpdateRequestDto;
import todo_app.dto.response.TaskResponseDto;
import todo_app.entity.Task;
import todo_app.repository.TaskRepository;

public class TaskServiceImpl implements TaskService {
	private final TaskRepository repository;
	private static long taskSequenceId = 1;
	
	public TaskServiceImpl() {
		this.repository = TaskRepository.getInstance();
	}
	
	private Long generatedTaskId() {
		return taskSequenceId++;
	}
	
	@Override
	public void createTask(TaskRequestDto dto) {
		Task newTask = new Task(generatedTaskId(), dto.getUserId()
				, dto.getContent(), dto.getTodoDate(), dto.isTodoCheck());
		repository.save(newTask);
		System.out.println("할일 등록이 완료되었습니다.");
	}

	@Override
	public List<TaskResponseDto> findByTodoDate(String userId, String todoDate) {
		List<TaskResponseDto> responseDto = null;
		
		try {
			List<Task> tasks = repository.findAll();
			
			responseDto = tasks.stream()
					.filter(task -> task.getUserId().equals(userId) && task.getTodoDate().equals(todoDate))
					.map(filteredTask -> new TaskResponseDto(filteredTask.getId(), filteredTask.getUserId(), filteredTask.getContent()
							, filteredTask.getTodoDate(), filteredTask.isTodoCheck()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return responseDto;
	}

	@Override
	public List<TaskResponseDto> findAllTask(String userId) {
		List<TaskResponseDto> responseDto = null;
		
		try {
			List<Task> tasks = repository.findAll();
			
			responseDto = tasks.stream()
					.filter(task -> task.getUserId().equals(userId))
					.map(filteredTask -> new TaskResponseDto(filteredTask.getId(), filteredTask.getUserId(), filteredTask.getContent()
							, filteredTask.getTodoDate(), filteredTask.isTodoCheck()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return responseDto;
	}

	@Override
	public void checkTodo(String userId, Long id) {
		try {
			Task task = repository.findTaskById(id)
					.orElseThrow(()-> new IllegalArgumentException("해당 ID의 할일은 존재하지 않습니다. ID: " + id));
			
			if (task.getUserId().equals(userId)) {
				task.setTodoCheck(!task.isTodoCheck());
				if (task.isTodoCheck()) {
					System.out.println("할일 체크가 완료되었습니다.");
				} else {
					System.out.println("할일 체크가 취소되었습니다.");
				}
			} else {
				System.out.println("로그인 계정에 등록된 할일이 아닙니다. 리스트 번호를 다시 입력해주세요. 로그인 ID: " + userId);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateTask(String userId, Long id, TaskUpdateRequestDto dto) {
		try {
			Task task = repository.findTaskById(id)
					.orElseThrow(()-> new IllegalArgumentException("해당 ID의 할일은 존재하지 않습니다. ID: " + id));
			
			if (task.getUserId().equals(userId)) {
				task.setContent(dto.getContent());
				task.setTodoDate(dto.getTodoDate());
				
				System.out.println(task);
				System.out.println("할일 수정이 완료되었습니다.");
			} else {
				System.out.println("로그인 계정에 등록된 할일이 아닙니다. 리스트 번호를 다시 입력해주세요. 로그인 ID: " + userId);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteTask(String userId, Long id) {
		try {
			Task task = repository.findTaskById(id)
					.orElseThrow(() -> new IllegalArgumentException("해당 ID의 할일은 존재하지 않습니다. ID: " + id));
			
			if (task.getUserId().equals(userId)) {
				repository.delete(task);
				System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("로그인 계정에 등록된 할일이 아닙니다. 리스트 번호를 다시 입력해주세요. 로그인 ID: " + userId);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	}

	@Override
	public List<TaskResponseDto> findIsTodoCheck(String userId, int isTodo) {
		List<TaskResponseDto> responseDto = null;
		
		try {
			List<Task> tasks = repository.findAll();
			
			if (isTodo == 1) {
				responseDto = tasks.stream()
						.filter(task -> task.getUserId().equals(userId) && task.isTodoCheck())
						.map(filteredTask -> new TaskResponseDto(filteredTask.getId(), filteredTask.getUserId()
								, filteredTask.getContent(), filteredTask.getTodoDate(), filteredTask.isTodoCheck()))
						.collect(Collectors.toList());
			} else {
				responseDto = tasks.stream()
						.filter(task -> task.getUserId().equals(userId) && !task.isTodoCheck())
						.map(filteredTask -> new TaskResponseDto(filteredTask.getId(), filteredTask.getUserId()
								, filteredTask.getContent(), filteredTask.getTodoDate(), filteredTask.isTodoCheck()))
						.collect(Collectors.toList());
			} 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return responseDto;
	}
	
	

}
