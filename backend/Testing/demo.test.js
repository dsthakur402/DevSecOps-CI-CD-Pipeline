const ToDoController = require('../controller/ToDoController');
const ToDoModel = require('../models/ToDoModel');

jest.mock('../models/ToDoModel');

describe('ToDoController', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  it('getToDos should send all todos', async () => {
    const mockFind = ToDoModel.find.mockResolvedValue(['todo1', 'todo2']);
    const req = {};
    const res = { send: jest.fn() };

    await ToDoController.getToDos(req, res);

    expect(mockFind).toHaveBeenCalledTimes(1);
    expect(res.send).toHaveBeenCalledWith(['todo1', 'todo2']);
  });

  it('saveToDo should save a todo and send the saved data', async () => {
    const mockCreate = ToDoModel.create.mockResolvedValue({ toDo: 'Test ToDo' });
    const req = { body: { toDo: 'Test ToDo' } };
    const res = { status: jest.fn().mockReturnThis(), send: jest.fn() };

    await ToDoController.saveToDo(req, res);

    expect(mockCreate).toHaveBeenCalledWith({ toDo: 'Test ToDo' });
    expect(res.status).toHaveBeenCalledWith(201);
    expect(res.send).toHaveBeenCalledWith({ toDo: 'Test ToDo' });
  });

  // Add similar tests for updateToDo and deleteToDo here
  it('updateToDo should update a todo and send a success message', async () => {
    const mockUpdate = ToDoModel.findByIdAndUpdate.mockResolvedValue();
    const req = { params: { id: '123' }, body: { toDo: 'Updated ToDo' } };
    const res = { send: jest.fn() };
  
    await ToDoController.updateToDo(req, res);
  
    expect(mockUpdate).toHaveBeenCalledWith('123', { toDo: 'Updated ToDo' });
    expect(res.send).toHaveBeenCalledWith('Updated Successfully....');
  });
  
  it('deleteToDo should delete a todo and send a success message', async () => {
    const mockDelete = ToDoModel.findByIdAndDelete.mockResolvedValue();
    const req = { params: { id: '123' } };
    const res = { send: jest.fn() };
  
    await ToDoController.deleteToDo(req, res);
  
    expect(mockDelete).toHaveBeenCalledWith('123');
    expect(res.send).toHaveBeenCalledWith('Deleted Successfully....');
  });

});