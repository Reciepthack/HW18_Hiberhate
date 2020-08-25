package homework18.service;

import homework18.dto.UserBaseD;
import homework18.dto.UserD;
import homework18.mapper.UserMapper;
import homework18.model.User;
import homework18.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean checkExistsById(Long id) {
        return userRepository.checkExistsById(id);
    }

    public int deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    public <T extends UserBaseD> void createUser(T t, Class<T> clazz) {
        userRepository.createUser(getUserFromD(t, clazz));
    }

    public <T extends UserBaseD> UserBaseD findUserById(Long id, Class<T> clazz) {
        return getDFromUser(userRepository.findUserById(id), clazz);
    }

    public <T extends UserD> UserD findUserByIdJoinFetch(Long id, Class<T> clazz) {
        return (UserD) getDFromUser(userRepository.findUserByIdJoinFetch(id), clazz);
    }

    public <T extends UserBaseD> List<UserBaseD> findAllUsers(Class<T> clazz) {
        return getDFromUsers(userRepository.findAllUsers(), clazz);
    }

    public  <T extends UserBaseD> void update(T t, Class<T> clazz) {
        userRepository.update(getUserFromDTO(t,clazz));
    }

    private  <T extends UserBaseD> User getUserFromDTO(T dto, Class<T> clazz) {
        if (clazz.getCanonicalName().equals(UserD.class.getCanonicalName())) {
            UserD userD = (UserD) d;
            return (userMapper.fromDToUser(userD, clazz));
        }
        return (userMapper.fromBaseDToUser(d, clazz));
    }

    private  <T extends UserBaseD> UserBaseD getDFromUser(User user, Class<T> clazz) {
        if (clazz.getCanonicalName().equals(UserD.class.getCanonicalName())) {
            return userMapper.toUserD(user);
        }
        return userMapper.toUserBaseD(user);
    }

    private  <T extends UserBaseD> List<UserBaseD> getDsFromUsers(List<User> users, Class<T> clazz) {
        List<UserBaseD> baseD = new ArrayList<>();
        for (User user : users) {
            baseD.add(getDFromUser(user, clazz));
        }
        return baseD;
    }
}