import React, { useState, useEffect } from 'react';
import api from './api';
import 'bootstrap/dist/css/bootstrap.min.css';
import moment from 'moment';

const Task = () => {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');
  const [newTaskDescription, setNewTaskDescription] = useState('');
  const [editedTask, setEditedTask] = useState({});
  const [taskIdToDelete, setTaskIdToDelete] = useState(null);

  useEffect(() => {
    // Fetch tasks from the backend on component mount
    fetchTasks();
  }, []);

  const fetchTasks = async () => {
    try {
      const response = await api.get('/get_all');
      setTasks(response.data);
    } catch (error) {
      console.error('Error fetching tasks:', error);
    }
  };

  const handleAddTask = async () => {
    try {
      // Check if both title and description are not empty
      if (!newTask || !newTaskDescription) {
        window.alert('Please fill in both title and description');
        return;
      }

      const response = await api.post('/create-task', {
        title: newTask,
        description: newTaskDescription,
      });

      setTasks([...tasks, response.data]);
      setNewTask('');
      setNewTaskDescription('');
    } catch (error) {
      console.error('Error adding task:', error);
    }
  };

  const handleUpdateTask = async () => {
    try {
      const response = await api.put(`/update-task/${editedTask.id}`, {
        title: editedTask.title,
        description: editedTask.description, // Include the description in the update
      });
      setTasks(
        tasks.map((task) => (task.id === editedTask.id ? response.data : task)),
      );
      setEditedTask({});
    } catch (error) {
      console.error(`Error updating task with id ${editedTask.id}:`, error);
    }
  };

  const handleDeleteTask = async (id) => {
    try {
      await api.delete(`/delete-task/${id}`);
      setTasks(tasks.filter((task) => task.id !== id));
      setTaskIdToDelete(null);
    } catch (error) {
      console.error(`Error deleting task with id ${id}:`, error);
    }
  };

  const handleEditTask = (task) => {
    setEditedTask(task);
  };

  const handleCancelEdit = () => {
    setEditedTask({});
  };

  const handleDeleteConfirmation = (id) => {
    setTaskIdToDelete(id);
  };

  const handleCancelDelete = () => {
    setTaskIdToDelete(null);
  };

  const formatTimestamp = (timestamp) => {
    try {
      return moment(timestamp).format('MMM DD, YYYY h:mm:ss A');
    } catch (error) {
      console.error('Error parsing timestamp:', error);
      return 'Invalid Date';
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="mb-4">Task Manager</h1>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Time</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {tasks.map((task) => (
            <tr key={task.id}>
              <td>{task.id}</td>
              <td>{task.title}</td>
              <td>{task.description}</td>
              <td>{formatTimestamp(task.time)}</td>
              <td>
                <button
                  className="btn btn-warning btn-sm"
                  onClick={() => handleEditTask(task)}
                  style={{ marginRight: '8px' }}
                >
                  Edit
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleDeleteConfirmation(task.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="mb-4">
        <input
          type="text"
          placeholder="Title"
          className="form-control"
          value={newTask}
          onChange={(e) => setNewTask(e.target.value)}
        />
        <input
          type="text"
          placeholder="Description"
          className="form-control mt-2"
          value={newTaskDescription}
          onChange={(e) => setNewTaskDescription(e.target.value)}
        />
        <button className="btn btn-success mt-2" onClick={handleAddTask}>
          Add Task
        </button>
      </div>

      {editedTask.id && (
        <div>
          <h2>Edit Task</h2>
          <input
            type="text"
            placeholder="Title"
            className="form-control"
            value={editedTask.title}
            onChange={(e) =>
              setEditedTask({ ...editedTask, title: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Description"
            className="form-control mt-2"
            value={editedTask.description}
            onChange={(e) =>
              setEditedTask({ ...editedTask, description: e.target.value })
            }
          />
          <button
            className="btn btn-primary mt-2"
            onClick={handleUpdateTask}
            style={{ marginRight: '8px' }}
          >
            Update Task
          </button>
          <button className="btn btn-secondary mt-2" onClick={handleCancelEdit}>
            Cancel
          </button>
        </div>
      )}

      {taskIdToDelete && (
        <div>
          <h2>Confirm Deletion</h2>
          <p>Are you sure you want to delete this task?</p>
          <button
            className="btn btn-danger"
            onClick={() => handleDeleteTask(taskIdToDelete)}
            style={{ marginRight: '8px' }}
          >
            Yes, Delete
          </button>
          <button
            className="btn btn-secondary ml-2"
            onClick={handleCancelDelete}
          >
            Cancel
          </button>
        </div>
      )}
    </div>
  );
};

export default Task;
