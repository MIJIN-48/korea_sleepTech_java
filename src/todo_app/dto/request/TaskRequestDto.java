package todo_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequestDto {
	private String userId;
	private String content;
	private String todoDate;
	private boolean todoCheck;
}
