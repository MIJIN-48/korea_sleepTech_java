package todo_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskResponseDto {
	private Long id;
	private String userId;
	private String content;
	private String todoDate;
	private boolean todoCheck;
}
