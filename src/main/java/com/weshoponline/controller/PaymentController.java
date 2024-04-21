package com.weshoponline.controller;

import com.weshoponline.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @GetMapping("/payment")
    public String index() {
        return "Here's a Payment Controller from Spring Boot -- 222!";
    }

    @RequestMapping("/home")
    public String home(){
        return "Here's a Payment Controller from Spring Boot! - HOME - RequestMapping";
    }

    @RequestMapping("/user")
    public User user(){
        User user = new User();
        user.setId(1);
        user.setName("Vishnu");
        user.setEmail("pingv@gmail.com");
        return user;
    }

    @RequestMapping(value = "/user2", method = RequestMethod.GET)
    public User user2(){
        User user = new User();
        user.setId(2);
        user.setName("Vishnu2");
        user.setEmail("pingv2@gmail.com");
        return user;
    }

    @GetMapping("user/{id}")
    public String returnID(@PathVariable int id){
        return "Path variable is: " +id;
    }

    @GetMapping("user/{id}/{id2}")
    public String returnTwoID(@PathVariable String id
    ,@PathVariable("id2") String name){
        return "Path variable is: " +id+ " :" +name;
    }

    @GetMapping("requestParam")
    public String requestParameterDemo(@RequestParam String queryParameter,
    @RequestParam(name="email", required=false) String emailID){
        //http://localhost:8081/requestParam?queryParameter=81818192xys&email=vis@gma.com
        return "Request Param is: " + queryParameter + "emailID: " + emailID;
    }

}