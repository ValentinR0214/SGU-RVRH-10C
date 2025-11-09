package mx.edu.utez.Server.users.control;

import mx.edu.utez.Server.users.model.UserDTO;
import mx.edu.utez.Server.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"*"})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Message> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveUser(
            @Validated(UserDTO.Register.class) @RequestBody UserDTO dto
    ) {
        return userService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateUser(
            @Validated(UserDTO.Modify.class) @RequestBody UserDTO dto
    ) {
        return userService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

}