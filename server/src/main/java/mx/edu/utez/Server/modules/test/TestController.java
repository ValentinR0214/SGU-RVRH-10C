package mx.edu.utez.Server.modules.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/sgu-api/test")
@CrossOrigin(origins = "*")
public class TestController {
    @GetMapping("")
    public ResponseEntity<?> getMessage() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("data", "OK");
        response.put("message", "API funcionando");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}