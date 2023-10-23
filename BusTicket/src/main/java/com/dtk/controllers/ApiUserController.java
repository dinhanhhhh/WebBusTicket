/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.User;
import com.dtk.service.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author GIGABYTE
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(this.userService.getUsers(null, 0), HttpStatus.OK);
    }

    @GetMapping("/users/getUser/{idUser}")
    public ResponseEntity<List<User>> getUserByID(@PathVariable(value = "idUser") int idUser) {
        List<User> user = new ArrayList<>();
        user.add(this.userService.getUserById(idUser));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/editUser")
    public boolean editUser(@RequestBody Map<String, String> param, HttpSession session) {
//        User u = (User) session.getAttribute("currentUser");
        try {
            User user = new User();
            user.setId(Integer.parseInt(param.get("id")));
            user.setFullname(param.get("fullname"));
            user.setGender(param.get("gender"));
            user.setUserRole(param.get("userRole"));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = param.get("birthday");
            user.setBirthday(formatter.parse(date));

            user.setAddress(param.get("address"));
            user.setPhone(param.get("phone"));
            user.setEmail(param.get("email"));
            user.setUsername(param.get("username"));
            if (param.get("password").equals(param.get("confirmPassword")) == true) {
                user.setPassword(param.get("password"));
            }
            if (param.get("password").equals("") == true && param.get("confirmPassword").equals("") == true) {
                user.setPassword("");
            }

            user.setAvatar(param.get("avatar"));
//            user.setAvatar("https://res.cloudinary.com/doe6rzwse/image/upload/v1663062934/rywurihjjegsyms1zew3.jpg");
            user.setActive(Boolean.TRUE);
            this.userService.editUser(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @DeleteMapping("/users/deleteUser/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "userId") int id) {
        this.userService.deleteUser(id);
    }
}
