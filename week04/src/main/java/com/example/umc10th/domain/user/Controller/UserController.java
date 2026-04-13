/*package com.example.umc10th.domain.user.Controller;

public class UserController {
}
*/
package com.example.umc10th.domain.user.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/test")
    public String testAPI() {
        return "Swagger 테스트 성공!";
    }
}