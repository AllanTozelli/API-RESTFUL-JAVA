package API.restful.TAREFAS;

import API.restful.TAREFAS.model.Task;
import API.restful.TAREFAS.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TarefasApplicationTests {

	@Autowired
	private TaskService taskService;

	@Test
	public void incluiTaks(){
		Task tasks = new Task();

	}
}
