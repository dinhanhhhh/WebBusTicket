/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository;

import com.dtk.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GIGABYTE
 */
public interface UserRepository {

    boolean addUser(User user);
    boolean deleteUser(int id);
    boolean editUser(User user);

    User getUserByUsername(String username);
    List<User> getUsers(Map<String, String> params, int page);
    List<User> getUserByRole(String userRole);
    User getUserById(int id);
    List<User> getUserByUsernameList(String username);
}
