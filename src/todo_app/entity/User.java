package todo_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private Long id; // 회원 고유 번호
	private String userId;
	private String password;
	private String name;
}
