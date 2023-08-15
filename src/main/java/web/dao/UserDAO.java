package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public void addUser(User user);

    public User getUser(long id);

    public void updateUser(User user);

    public void deleteUser(User user);
}
