package hongik.project.controller;

import hongik.project.domain.Item;
import hongik.project.domain.User;
import hongik.project.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Getter
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/new")
    public String createForm(Model model){
        model.addAttribute("userForm", new UserForm());
        return "user/CreateUserForm";
    }

    @PostMapping(value = "/user/new")
    public String create( UserForm form, BindingResult result){
        if (result.hasErrors()) {
            return "user/CreateUserForm";
        }

        User user = new User();
        user.setName(form.getName());
        user.setPassword(form.getPassword());
        user.setAge(form.getAge());
        userService.join(user);
        return "redirect:/user/signupSuccess";
    }

    @GetMapping("/user/signupSuccess")
    public String signupSuccess() {
        return "user/signupSuccess"; // 회원가입 성공 페이지
    }

    // 로그인 폼 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";  // login.html
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            model.addAttribute("username", user.getName());
            return "user/loginsuccess";  // 바로 성공 페이지로 포워딩
        } else {
            model.addAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "user/login";
        }
    }


}