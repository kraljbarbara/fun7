package si.barbarak.fun7.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.barbarak.fun7.exception.DoesNotExistException;
import si.barbarak.fun7.model.User;
import si.barbarak.fun7.service.IUserService;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    final
    IUserService userService;

    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/users/{uuid}")
    public ResponseEntity<User> getUserByUUID(@PathVariable String uuid) {
        try {
            return ResponseEntity.ok(userService.getUser(uuid));
        } catch (DoesNotExistException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/users/{uuid}")
    public ResponseEntity<?> disableUserByUUID(@PathVariable String uuid) {
        try {
            userService.disableUser(uuid);
        } catch (DoesNotExistException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }
}
