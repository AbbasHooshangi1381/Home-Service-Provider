package com.example.springbootfinal.controller;

import com.example.springbootfinal.service.impl.CaptchaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("/captcha")
    public ResponseEntity<String> getCaptcha(HttpSession session) {
        String captcha = captchaService.generateCaptchaToken(session);
        return ResponseEntity.ok(captcha);
    }
    @PostMapping("/verify-captcha")
    public ResponseEntity<String> verifyCaptcha(@Valid @RequestBody Map<String, String > body, HttpSession session) {
        boolean valid = captchaService.validateCaptcha(body.get("captchaAnswer"), session);
        return valid ? ResponseEntity.ok("Captcha is valid") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid captcha");
    }
}
