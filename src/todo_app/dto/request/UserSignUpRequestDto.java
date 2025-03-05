package todo_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignUpRequestDto {
	private String userId;
	private String password;
	private String passwordCheck;
	private String name;
}
