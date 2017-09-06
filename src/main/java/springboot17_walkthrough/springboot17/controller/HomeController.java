package springboot17_walkthrough.springboot17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot17_walkthrough.springboot17.models.Role;
import springboot17_walkthrough.springboot17.models.User;
import springboot17_walkthrough.springboot17.repository.RoleRepository;
import springboot17_walkthrough.springboot17.repository.UserRepository;
import springboot17_walkthrough.springboot17.service.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.boot.autoconfigure.security.SecurityAuthorizeMode.ROLE;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value="/register",method= RequestMethod.GET)
            public String showRegistrationPage(Model model)
    {
        model.addAttribute("user",new User());
        return "registration";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public  String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                           BindingResult bindingResult,Model model){
        model.addAttribute("user", user);
        if(bindingResult.hasErrors()){
            return"registration";
        }
        else
        {
            userService.saveUser(user);
            model.addAttribute("message","User Account Successfully Created");
        }
        return "login";
    }

    @RequestMapping("/")
    public String home()
    {
        return"welcome";
    }

    @RequestMapping("/index")
    public String index(){
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
//        Iterable<Role>test= roleRepository.findAllById(new Long(1));
//        for(Role item : test)
//        {
//            System.out.println(item.getRole());
//        }
        return"signup";
    }
    @PostMapping("/signup")
    public String postNewUser(@ModelAttribute("newuser") User otheruser){

        otheruser.setEnabled(true);

//        Role newrole=roleRepository.findOne(new Long(2));
        //this finds the user
        Role newrole=roleRepository.findByRole("USER");
//        newrole.setRole(newrole.getRole() );
        otheruser.addRole(newrole);

//       roleRepository.save(newrole);
//        Collection<User>newcolluser=
//         newrole.setUsers();
//
//        Set<Role> roles= new HashSet<>();
//        roles.add(newrole);
//        otheruser.setRoles(roles);
        userRepository.save(otheruser);


        return"postuser";
    }
}
