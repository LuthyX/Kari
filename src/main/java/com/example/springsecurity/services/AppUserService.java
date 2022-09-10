package com.example.springsecurity.services;

import com.example.springsecurity.models.*;
import com.example.springsecurity.models.Package;
import com.example.springsecurity.repositories.AppUserRepository;
import com.example.springsecurity.repositories.CustomerRepository;
import com.example.springsecurity.repositories.PackageRepository;
import com.example.springsecurity.repositories.RoleRepository;
import com.example.springsecurity.request.DeliveryTypeRequest;
import com.example.springsecurity.request.PackageRequest;
import com.example.springsecurity.request.StatusRequest;
import com.example.springsecurity.request.TrackCodeRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AppUserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;

    private final CustomerRepository customerRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final PackageRepository packageRepository;
//    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRole().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public String saveUser(AppUser appUser
    ) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Email is already taken.. Try Another Email");
        };
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        if(appUser.getUserType().name() == "CUSTOMER") {
            String rol = "ROLE_USER";
            Role role = roleRepository.findByName(rol);
            appUser.getRole().add(role);
            appUser.setPassword(encodedPassword);
            appUserRepository.save(appUser);
            Customer customer = new Customer(appUser, appUser.getId());
            customerRepository.save(customer);
            return "saved";
        }
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        return "saved";


        }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();


//        String token = UUID.randomUUID().toString();
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                appUser
//        );
//        confirmationTokenService.saveConfirmationToken(confirmationToken);
//        // TODO : Send Email
//
//        return token;
    }
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
    public void addRoleToUser(String email, String roleName) {
        AppUser user = appUserRepository.findByEmail(email).orElseThrow(()-> new IllegalStateException("User not found"));
        Role role = roleRepository.findByName(roleName);
        user.getRole().add(role);
    }
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    public String addPackage(PackageRequest packageRequest){
        Customer customer = customerRepository.findByCustomerEmail(packageRequest.getEmail());
        String trackcode = UUID.randomUUID().toString();
        Package aPackage = new Package(customer, customer.getId(),trackcode,packageRequest.getWeight());
        packageRepository.save(aPackage);
        return "registered Package";
    }

    public String findStatusByTrackcode(TrackCodeRequest trackcode){
        Package aPackage = packageRepository.findByTrackcode(trackcode.getTrackcode());
        if (aPackage == null){
            throw new IllegalStateException("Code is not valid");
        }
        String status = String.valueOf(aPackage.getStatus());
        return status;


    }

    public List<Package>getAllPackages(){
        return packageRepository.findAll();
    }

    public String changePackageStatus(StatusRequest request){
        Package aPackage = packageRepository.findById(request.getId()).orElseThrow(()-> new IllegalStateException("Package of that id does not exist"));
        aPackage.setStatus(request.getStatus());
        return "Status Changed";
    }

    public String ChangeDeliveryType(DeliveryTypeRequest request){
       Package aPackage = packageRepository.findById(request.getId()).orElseThrow(()-> new IllegalStateException("Id could not be found"));
       aPackage.setDelivery(request.getDeliveryType());
       packageRepository.save(aPackage);
       return "Set";
    }

    public List<Package> getPackagesByCustomer(Long id) {
        return packageRepository.findByCustomerid(id);
    }
}
