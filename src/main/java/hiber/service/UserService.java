package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void deleteAllUsers();
    User getOwner(String car_name, String car_series);
}
