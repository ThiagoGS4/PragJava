//package com.antiprag.prag;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import com.antiprag.prag.domain.Privilege;
//import com.antiprag.prag.domain.Roles;
//import com.antiprag.prag.repository.PrivilegeRepository;
//import com.antiprag.prag.repository.RolesRepository;
//
//import jakarta.transaction.Transactional;
//
//@Component
//public class SetupDataLoader implements
//  ApplicationListener<ContextRefreshedEvent> {
//
//    boolean alreadySetup = false;
// 
//    @Autowired
//    private RolesRepository roleRepository;
// 
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
// 
//    @Override
//    @Transactional
//    public void onApplicationEvent(ContextRefreshedEvent event) {
// 
//        if (alreadySetup)
//            return;
//        Privilege readPrivilege
//          = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege
//          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
// 
//        List<Privilege> adminPrivileges = Arrays.asList(
//          readPrivilege, writePrivilege);
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//        createRoleIfNotFound("ROLE_STAFF", Arrays.asList(writePrivilege));
//        alreadySetup = true;
//    }
//
//    @Transactional
//    Privilege createPrivilegeIfNotFound(String name) {
// 
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    Roles createRoleIfNotFound(
//      String name, Collection<Privilege> privileges) {
// 
//        Roles role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new Roles(name);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//        return role;
//    }
//}
