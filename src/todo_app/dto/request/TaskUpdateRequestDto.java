package todo_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskUpdateRequestDto {
	private String content;
	private String todoDate;
}
