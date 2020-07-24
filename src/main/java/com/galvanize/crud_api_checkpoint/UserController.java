package com.galvanize.crud_api_checkpoint;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository)
    {
        this.repository = repository;
    }
    @GetMapping("/list")
    public Iterable<User> getAll()
    {
        return this.repository.findAll();
    }
    @PostMapping("/save")
    public User create(@RequestBody User user)
    {
        return this.repository.save(user);
    }
    @GetMapping("/by-id")
    public User byId(@RequestParam("id") long id)
    {
        return this.repository.findById(id);
    }
    @PatchMapping("/id/{id}")
    public User patch (@PathVariable Long id, @RequestBody User user)
    {
        User userToUpdate = this.repository.findById(id).get();

        userToUpdate.setEmail(user.getEmail());
        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }

        return this.repository.save(userToUpdate);
    }
    @DeleteMapping("/delete-id")
    public void DbyId(@RequestParam("id") long id)
    {
        this.repository.deleteById(id);
    }
    @PostMapping("/users/authenticate")
    public Map<String, Object> authenticate(@RequestBody User user)
    {
        Map<String, Object> results = new HashMap<>();
        Long id = this.repository.authenticate(user.getEmail(), user.getPassword());
        if (id != null) {
            user.setId(id);
            results.put("authenticated", true);
            results.put("user", user);
        } else {
            results.put("authenticated", false);
        }
        return results;
    }
}
