package com.timothy.restfulwebservices.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount=0;
    static{
        users.add(new User(++userCount,"paul", LocalDate.now().minusYears(23)));
        users.add(new User(++userCount,"timothy", LocalDate.now().minusYears(23)));
        users.add(new User(++userCount,"daniel", LocalDate.now().minusYears(13)));
    }
    public List<User> findAll(){
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId()==(id);
        return users.stream().filter(predicate).findFirst().get();
    }


    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
