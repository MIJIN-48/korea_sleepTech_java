package todo_app;

import java.util.List;
import java.util.Scanner;

import todo_app.controller.TaskController;
import todo_app.controller.UserController;
import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.request.TaskUpdateRequestDto;
import todo_app.dto.request.UserSignInRequestDto;
import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.TaskResponseDto;
import todo_app.dto.response.UserResponseDto;

public class App {
	private static final Scanner sc = new Scanner(System.in);
	private static final UserController USER_CONTROLLER = new UserController();
	private static final TaskController TASK_CONTROLLER = new TaskController();
	
	private static void userMenu() {
		System.out.println("<<< ToDo 리스트 프로그램 >>>");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 로그아웃");
		System.out.println("====================");
		System.out.println("4. 회원 정보 조회(전체)");
		System.out.println("5. 회원 정보 조회(단건)");
		System.out.println("6. 회원 삭제");
		System.out.println("====================");
		System.out.println("7. 할일 등록");
		System.out.println("8. 할일 조회 (단건)");
		System.out.println("9. 할일 조회 (전체)");
		System.out.println("10. 할일 조회 (완료 여부)");
		System.out.println("11. 할일 체크");
		System.out.println("12. 할일 수정");
		System.out.println("13. 할일 삭제");
		System.out.println("====================");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴를 선택하세요>> ");
	}
	
	private static int getChoice() {
		while(!sc.hasNextInt()) {
			System.out.println("숫자를 입력해주세요.");
			sc.nextLine();
		}
		
		int choice = sc.nextInt();
		sc.nextLine(); // 버퍼 처리
		return choice;
	}
	
	private static String getInput(String prompt) {
		System.out.println(prompt + ": ");
		return sc.nextLine().trim();
	}
	
	private static long getIdInput() {
		String input = getInput("변경할 리스트 번호를 입력하세요");
		return Long.parseLong(input);
	}

	// 1. 회원가입
	private static UserSignUpRequestDto createUserRequest() {
		UserSignUpRequestDto dto = null;
		
		try {
			String userId = getInput("아이디를 입력하세요");
			String password = getInput("비밀번호를 입력하세요");
			String passwordCheck = getInput("비밀번호를 한번 더 입력하세요");
			String name = getInput("이름을 입력하세요");
			
			dto = new UserSignUpRequestDto(userId, password, passwordCheck, name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}
	
	// 2. 로그인
	private static UserSignInRequestDto loginRequest() {
		UserSignInRequestDto dto = null;
		
		try {
			String userId = getInput("아이디를 입력하세요");
			String password = getInput("비밀번호를 입력하세요");
			
			dto = new UserSignInRequestDto(userId, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}
	
	// 7. 할일 등록
	private static TaskUpdateRequestDto updateTaskRequest() {
		TaskUpdateRequestDto dto = null;
		
		try {
			String content = getInput("내용을 입력하세요");
			String todoDate = getInput("실행일을 입력하세요 (YYYY-MM-DD)");

			dto = new TaskUpdateRequestDto(content, todoDate);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}
	
	// 11. 할일 수정
	private static TaskRequestDto createTaskRequest() {
		TaskRequestDto dto = null;
		
		try {
			String content = getInput("내용을 입력하세요");
			String todoDate = getInput("실행일을 입력하세요 (YYYY-MM-DD)");

			dto = new TaskRequestDto(USER_CONTROLLER.getLoggedId(), content, todoDate, false);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}
	
	private static boolean processChoice(int choice) {
		switch (choice) {
		// 회원 관련 기능
		case 1: {
			UserSignUpRequestDto requestDto = createUserRequest();
			USER_CONTROLLER.signUpUser(requestDto);
			break;
		}
		case 2: {
			if (!USER_CONTROLLER.loginCheck()) {
				UserSignInRequestDto requestDto = loginRequest();
				USER_CONTROLLER.login(requestDto);
			} else {
				System.out.println("이미 로그인된 상태입니다.");
			}
			break;
		}
		case 3: {
			USER_CONTROLLER.logout();
			break;
		}
		case 4: {
			List<UserResponseDto> dtos = USER_CONTROLLER.findAllUser();
			if (dtos.isEmpty()) {
				System.out.println("등록된 회원이 없습니다.");
			} else {
				dtos.forEach(System.out::println);
			}
			break;
		}
		case 5: {
			String id = getInput("조회할 아이디를 입력해주세요");
			UserResponseDto dto = USER_CONTROLLER.findByUserId(id);
			
			if (dto == null) {
				System.out.println("등록된 회원이 없습니다.");
			} else {
				System.out.println(dto);
			}
			break;
		}
		case 6: {
			String id = getInput("삭제할 아이디를 입력해주세요");
			UserResponseDto dto = USER_CONTROLLER.findByUserId(id);
			
			if (dto == null) {
				System.out.println("등록된 회원이 없습니다.");
			} else {
				USER_CONTROLLER.deleteUser(id);
				System.out.println("해당 아이디를 삭제하였습니다. ID: " + id);
			}
			break;
		}
		// ToDo 리스트
		case 7: {
			if (USER_CONTROLLER.loginCheck()) {
				TaskRequestDto requestDto = createTaskRequest();
				TASK_CONTROLLER.createTask(requestDto);
			} else {
				System.out.println("로그인 후 이용해 주세요.");
			}
			break;
		}
		case 8: {
			String date = getInput("할일 조회할 날짜를 입력하세요");
			List<TaskResponseDto> dto = TASK_CONTROLLER.findTaskByTodoDate(USER_CONTROLLER.getLoggedId(), date);
			
			if (dto.isEmpty()) {
				System.out.println("등록된 할일이 없습니다.");
			} else {
				System.out.println(dto);
			}
			break;
		}
		case 9: {
			List<TaskResponseDto> dto = TASK_CONTROLLER.findTaskByUserId(USER_CONTROLLER.getLoggedId());
			
			if (dto.isEmpty()) {
				System.out.println("등록된 할일이 없습니다.");
			} else {
				System.out.println(dto);
			}
			break;
		}
		case 10: {
			String result = getInput("완료 /  미완료");
			
			List<TaskResponseDto> dto = TASK_CONTROLLER.findIsTodo(USER_CONTROLLER.getLoggedId(), result);
			
			if (dto.isEmpty()) {
				System.out.println("등록된 할일이 없습니다.");
			} else {
				System.out.println(dto);
			}
			break;
		}
		case 11: {
			long id = getIdInput();
			TASK_CONTROLLER.checkTodo(id);
			break;
		}
		case 12: {
			long id = getIdInput();
			TaskUpdateRequestDto requestDto = updateTaskRequest();
			TASK_CONTROLLER.updateTask(id, requestDto);
			break;
		}
		case 13: {
			long id = getIdInput();
			TASK_CONTROLLER.deleteTask(id);
			break;
		}
		case 0: {
			System.out.println("프로그램을 종료합니다.");
			return false;
		}
		default: {
			System.out.println("잘못된 선택입니다. 유효한 메뉴를 선택해주세요.");
			break;
		}
		}
		return true;
	}
	
	public static void main(String[] args) {
		try {
			while (true) {
				userMenu();
				int choice = getChoice();

				if (!processChoice(choice)) break;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// 예외 발생과 상관없이 반드시 실행 보장
			sc.close();
		}
	}
}
