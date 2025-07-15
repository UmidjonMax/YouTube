package dasturlash.uz.controller;

import dasturlash.uz.dto.auth.LoginDTO;
import dasturlash.uz.dto.auth.LoginResponseDTO;
import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }


    @GetMapping("/registration/email/verification/{jwt}")
    public ResponseEntity<String> registration(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok(authService.regVerification(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}

