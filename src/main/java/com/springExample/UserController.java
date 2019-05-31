package com.springExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // Tells spring that this class is a controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<User> index() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User show(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }

    // search user by name
    @PostMapping("/user/search")
    public List<User> search(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        return userRepository.findByName(name);
    }

    @PostMapping("/user")
    public User create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        int age = Integer.parseInt(body.get("age"));
        return userRepository.save(new User(name, age));
    }

    @PutMapping("/user/{id}")
    public User update(@PathVariable int id, @RequestBody Map<String, String> body) {
        User user = userRepository.findById(id).orElse(null);
        String name = body.get("name");
        int age = Integer.parseInt(body.get("age"));
        user.setName(name);
        user.setAge(age);
        return userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public boolean delete(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
        return true;
    }


}
