package com.example.javi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {
	@Autowired

	private taskRepository taskRepository;

	// Listar Todo
	@GetMapping("/listarTareas")
	public List<Task> getTasks() {

		return (List<Task>) this.taskRepository.findAll();
	}

	// Borrar Todo
	@DeleteMapping("/borrarTareas")
	public void deleteTasks() {

		taskRepository.deleteAll();
	}

	// Borrar por Id
	@DeleteMapping("/delTask/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable long id) {

		taskRepository.deleteById(id);

		return new ResponseEntity<>("User deleted.", HttpStatus.ACCEPTED);
	}

	// Actualizar Tareas
	@PutMapping("/updateTask/{id}/{description}/{status}")
	public ResponseEntity<String> updateTask(@PathVariable long id, @PathVariable String description,
			@PathVariable String status) {

		List<Task> allTasks = (List<Task>) this.taskRepository.findAll();

		for (int i = 0; i < allTasks.size(); ++i) {

			if (allTasks.get(i).getId() == id) {
				allTasks.get(i).setDescription(description);
				allTasks.get(i).setStatus(status);
				taskRepository.save(allTasks.get(i));
			}
		}

		return new ResponseEntity<>("User Updated.", HttpStatus.ACCEPTED);
	}

	// Añadir tareas
	@PostMapping("/task")
	public Task postTask(@RequestBody Task task) {
		return this.taskRepository.save(task);
	}

}
