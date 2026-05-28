// package com.antiprag.prag;

// import java.util.Arrays;
// import java.util.Collection;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationListener;
// import org.springframework.context.event.ContextRefreshedEvent;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.antiprag.prag.domain.Privileges;
// import com.antiprag.prag.domain.Roles;
// import com.antiprag.prag.repository.PrivilegesRepository;
// import com.antiprag.prag.repository.RolesRepository;
// import com.antiprag.prag.repository.UsersRepository;

// import jakarta.transaction.Transactional;

// @Component
// public class SetupDataLoader implements
//   ApplicationListener<ContextRefreshedEvent> {

//     boolean alreadySetup = false;

//     @Autowired
//     private UsersRepository usersRepository;
 
//     @Autowired
//     private RolesRepository roleRepository;
 
//     @Autowired
//     private PrivilegesRepository privilegeRepository;
 
//     @Autowired
//     private PasswordEncoder passwordEncoder;
 
//     @Override
//     @Transactional
//     public void onApplicationEvent(ContextRefreshedEvent event) {
 
//         if (alreadySetup)
//             return;
//         Privileges readPrivilege
//           = createPrivilegeIfNotFound("READ_PRIVILEGE");
//         Privileges writePrivilege
//           = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
 
//         List<Privileges> adminPrivileges = Arrays.asList(
//           readPrivilege, writePrivilege);
//         createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//         createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

//         /*Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//         User user = new User();
//         user.setFirstName("Test");
//         user.setLastName("Test");
//         user.setPassword(passwordEncoder.encode("test"));
//         user.setEmail("test@test.com");
//         user.setRoles(Arrays.asList(adminRole));
//         user.setEnabled(true);
//         userRepository.save(user);
//         */
//         alreadySetup = true;
//     }

//     @Transactional
//     Privileges createPrivilegeIfNotFound(String name) {
 
//         Privileges privilege = privilegeRepository.findByName(name);
//         if (privilege == null) {
//             privilege = new Privileges();
//             privilegeRepository.save(privilege);
//         }
//         return privilege;
//     }

//     @Transactional
//     Roles createRoleIfNotFound(
//       String name, Collection<Privileges> privileges) {
 
//         Roles role = roleRepository.findByName(name);
//         if (role == null) {
//             role = new Roles();
//             role.setPrivileges(privileges);
//             roleRepository.save(role);
//         }
//         return role;
//     }
// }
