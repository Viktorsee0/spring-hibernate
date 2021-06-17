package by.tms.controller;

import by.tms.entity.Address;
import by.tms.entity.User;
import by.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String save(){
        return "home";
    }

    @PostMapping("save")
    public String save(User user, Address address){
        user.setAddress(address);
        userService.save(user);
        return "redirect:/user";
    }

    @PostMapping("findByUserName")
    public String findByUserName(String username, Model model){
        User byUserName = userService.findByUserName(username);
        model.addAttribute("byUserName", byUserName);
        return "home";
    }

    @PostMapping("updateName")
    public String updateName(String name, long id){
        userService.updateName(name, id);
        return "home";
    }

    @PostMapping("delete")
    public String delete(long id){
        userService.delete(id);
        return "home";
    }

    @PostMapping("findAll")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "home";
    }
}
