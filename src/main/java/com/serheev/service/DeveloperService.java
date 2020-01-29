package com.serheev.service;

import com.serheev.model.DeveloperEntity;
import com.serheev.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public DeveloperEntity save(DeveloperEntity developer) {
        return developerRepository.save(developer);
    }

    public DeveloperEntity findById(Long id){
        return developerRepository.findById(id).orElse(null);
    }

    public List<DeveloperEntity> findAll(){
        return (List<DeveloperEntity>) developerRepository.findAll();
    }

    public Long count(){
        return developerRepository.count();
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }

    public void delete(DeveloperEntity developer) {
        developerRepository.delete(developer);
    }

    public void truncate() {
        developerRepository.deleteAll();
    }
}
