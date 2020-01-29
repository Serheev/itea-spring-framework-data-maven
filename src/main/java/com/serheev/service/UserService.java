package com.serheev.service;

import com.serheev.model.UserEntity;
import com.serheev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> findAll(){
        return (List<UserEntity>) userRepository.findAll();
    }

    public Long count(){
        return userRepository.count();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void delete(UserEntity user) {
        userRepository.delete(user);
    }

    public void truncate() {
        userRepository.deleteAll();
    }

    public void updateAdditionalInfo(UserEntity user, Map<String, Object> addInfo){
                userRepository.updateAdditionalInfo(user.getId(), addInfo);
    }
}
