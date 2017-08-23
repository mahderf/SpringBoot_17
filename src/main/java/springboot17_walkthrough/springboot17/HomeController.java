package springboot17_walkthrough.springboot17;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return"index";
    }
@RequestMapping("/login")
    public String logon(){
        return"login";
}
}
