package com.serheev.service;

import com.serheev.model.CompanyEntity;
import com.serheev.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyEntity save(CompanyEntity company) {
        return companyRepository.save(company);
    }

    public CompanyEntity findById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    public List<CompanyEntity> findAll(){
        return (List<CompanyEntity>) companyRepository.findAll();
    }

    public Long count(){
        return companyRepository.count();
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public void delete(CompanyEntity company) {
        companyRepository.delete(company);
    }

    public void truncate() {
        companyRepository.deleteAll();
    }

}
