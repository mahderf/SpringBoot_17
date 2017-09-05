package springboot17_walkthrough.springboot17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot17_walkthrough.springboot17.models.User;
import springboot17_walkthrough.springboot17.repository.UserRepository;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index()
    {
        return"index";
    }
@RequestMapping("/login")
    public String logon(){
        return"login";
}

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/secure")
    public String secure(){
        return "secure";
    }
    @RequestMapping("/signup")
    public String signUp( Model model){
       User newuser=new User();
       model.addAttribute("newuser", newuser);
        return"signup";
    }
    @PostMapping("/signup")
    public String postNewUser(@ModelAttribute("newuser") User otheruser){
        otheruser.setEnabled(true);
        userRepository.save(otheruser);
        return"postuser";

    }
}
