package todo_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
	private Long id;
	private String userId;
	private String content;
	private String todoDate;
	private boolean todoCheck = false;
}
