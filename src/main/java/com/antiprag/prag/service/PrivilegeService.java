//package com.antiprag.prag.service;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.antiprag.prag.domain.Privilege;
//import com.antiprag.prag.repository.PrivilegeRepository;
//
//@Service
//public class PrivilegeService {
//
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
//
//    public PrivilegeService(PrivilegeRepository privilegeRepository) {
//        this.privilegeRepository = privilegeRepository;
//    }
//
//    public Privilege getPrivilege(Integer id) {
//        return privilegeRepository.findById(id).orElse(null);
//    }
//
//    public List<Privilege> ListPrivilege() {
//        return privilegeRepository.findAll();
//    }
//
//    public void deletarPrivilege(int idPrivilege) {
//        privilegeRepository.deleteById(idPrivilege);
//    }
//
//    public void alterarPrivilege(Privilege Privilege) {
//        privilegeRepository.save(Privilege);
//    }
//
//    public void inserirPrivilege(Privilege privilege) {
//        privilegeRepository.save(privilege);
//    }
//}