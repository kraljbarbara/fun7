package si.barbarak.fun7.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import si.barbarak.fun7.model.Action;
import si.barbarak.fun7.model.UserStatus;
import si.barbarak.fun7.service.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    final
    private IUserService userService;

    final
    private ISupportService supportService;

    final
    private IMultiplayerService multiplayerService;

    final
    private IAdService adService;

    final
    private IAuditLogService auditLogService;

    public UserController(ISupportService supportService, IMultiplayerService multiplayerService, IUserService userService, IAdService adService, IAuditLogService auditLogService) {
        this.supportService = supportService;
        this.multiplayerService = multiplayerService;
        this.userService = userService;
        this.adService = adService;
        this.auditLogService = auditLogService;
    }

    @GetMapping("/status")
    public ResponseEntity<UserStatus> check(@RequestParam("timezone") String timezone,
                                            @RequestParam("userId") String userId,
                                            @RequestParam("cc") String cc) {
        if (!userService.isActive(userId)) {
            return ResponseEntity.notFound().build();
        }

        UserStatus status;
        try {
            status = new UserStatus(
                    multiplayerService.isEnabled(userId, cc) ? "enabled" : "disabled",
                    supportService.isEnabled() ? "enabled" : "disabled",
                    adService.isEnabled(cc) ? "enabled" : "disabled"
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        auditLogService.writeAuditLog(userId, Action.CHECK);

        return ResponseEntity.ok(status);
    }
}
