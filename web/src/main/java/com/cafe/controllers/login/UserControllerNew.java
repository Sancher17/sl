//package com.cafe.controllers.login;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.ServletException;
//import java.util.*;
//
//@RestController
//@RequestMapping("/users")
//public class UserControllerNew {
//
//    private final Map<String, List<String>> userDb = new HashMap<>();
//
//    public UserControllerNew() {
//        userDb.put("tom", Arrays.asList("user"));
//        userDb.put("sally", Arrays.asList("user", "admin"));
//    }
//    @GetMapping("all")
//    public Map<String, List<String>> getAll(){
//        return userDb;
//    }
//
//    @PostMapping("login")
//    public LoginResponse login(@RequestBody  UserLogin login) throws ServletException {
//        if (login.name == null || !userDb.containsKey(login.name)) {
//            throw new ServletException("Invalid login");
//        }
//        return new LoginResponse(Jwts.builder().setSubject(login.name)
//            .claim("roles", userDb.get(login.name)).setIssuedAt(new Date())
//            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
//    }
//
//    @PostMapping()
//    public LoginResponse loginNew(@RequestParam(value = "login") UserLogin login) {
//        return new LoginResponse(Jwts.builder()
//                .setSubject(login.name)
//                .claim("roles", userDb.get(login.name))
//                .setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS256, "secretkey")
//                .compact());
//            }
//
//
//    @SuppressWarnings("unused")
//    private static class UserLogin {
//        public String name;
//        public String password;
//    }
//
//    @SuppressWarnings("unused")
//    private static class LoginResponse {
//        public String token;
//
//        public LoginResponse(final String token) {
//            this.token = token;
//        }
//    }
//}
