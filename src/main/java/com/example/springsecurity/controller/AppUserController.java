package com.example.springsecurity.controller;

import com.example.springsecurity.models.AppUser;
import com.example.springsecurity.models.Customer;
import com.example.springsecurity.models.Package;
import com.example.springsecurity.models.Role;
import com.example.springsecurity.request.*;
import com.example.springsecurity.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AppUserController {

    private  final AppUserService appUserService;

    @GetMapping( "/users")
    private ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(appUserService.getUsers());
    }


    @PostMapping( "/save/user")
    private ResponseEntity<?>saveUser(@RequestBody AppUser user){
        if (appUserService.findbyemail(user.getEmail()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveUser(user));
    }

    @PostMapping( "/role/save")
    private ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

//    @GetMapping( "/user")
//    private ResponseEntity<AppUser>getUser(@RequestParam("username") String username){
//        return ResponseEntity.ok().body(appUserService.getUser(username));
//    }

    @PostMapping( "/role/addtouser")
    private ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        appUserService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customers")
    private List<Customer> getCustomers(){
        return appUserService.getCustomers();
    }

    @PostMapping("/package/add")
    private String addPackage(@RequestBody PackageRequest packageRequest ){
        return appUserService.addPackage(packageRequest);
    }

    @GetMapping("/packages")
    private List<Package> getAllPackages(){
        return appUserService.getAllPackages();
    }

    @PostMapping("/package/find")
    private String findPackageByCode(@RequestBody TrackCodeRequest trackcode){
        return appUserService.findStatusByTrackcode(trackcode);
    }

    @PutMapping("/package/changestatus")
    private String ChangePackageStatus(@RequestBody StatusRequest statusRequest){
        return appUserService.changePackageStatus(statusRequest);
    }

    @PutMapping("/package/delivery")
    private String DeliveryType(@RequestBody DeliveryTypeRequest request){
        return appUserService.ChangeDeliveryType(request);
    }

    @GetMapping("package/{id}")
    private List<Package> getPackageByCustomer(@PathVariable("id") Long id){
        return appUserService.getPackagesByCustomer(id);

    }

}

