package fi.jori.todo.service

import fi.jori.todo.model.Task
import java.util.Date
import scala.collection.mutable.Map

class TaskService {
  
  
  var tasks = Map.empty[String, Task]

  def create(task: Task) = {
    
    val uuid: String = java.util.UUID.randomUUID().toString();
    val newTask = Task(id=Some(uuid), 
        todo=Some(new Date), 
        topic=task.topic, 
        explanation=task.explanation)
        
    tasks += (uuid -> newTask)
    newTask
  }
  
  def start(task: Task) = {
    
    val existingTask = tasks.get(task.id.toString()).get
    val updateTask = Task(id=existingTask.id, 
        todo=existingTask.todo, 
        topic=task.topic, 
        explanation=task.explanation,
        ongoing=Some(new Date))
    
    tasks(existingTask.id.get) = existingTask
    existingTask
  }
  
  def finish(task: Task) = {
    
    val existingTask = tasks.get(task.id.toString()).get
    val finishTask = Task(id=existingTask.id, 
        todo=existingTask.todo, 
        topic=task.topic, 
        explanation=task.explanation,
        ongoing=existingTask.ongoing,
        finished=Some(new Date))
        
   tasks(finishTask.id.get) = finishTask
   finishTask
  }
}