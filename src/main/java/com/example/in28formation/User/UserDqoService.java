package com.example.in28formation.User;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;




@Component
public class UserDqoService {
    public static List<User> users = new ArrayList<>();

    private static int usersCount = 4;

    static {
        users.add(new User(1,"issame",new Date()));
        users.add(new User(2,"adem",new Date()));
        users.add(new User(3,"yacine",new Date()));
        users.add(new User(4,"faycal",new Date()));
    }
    public List<User> FindAll(){
        return users;
    }
    public User saveuser(User user){
        if(user.getId()==null){
       user.setId(++usersCount);
    }
        users.add(user);
        return user;
    }
    public User finduser(int id){
        for(User user:users) {
            if(user.getId()==id) {
                return user;
            }
        }
        return null;
    }
    public User deleteuser(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId()==id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    }

