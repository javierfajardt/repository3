package com.example.javi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface taskRepository extends CrudRepository<Task, Long> {

	//Interfaz que nos permite utilizar métodos de CrudRepository
}