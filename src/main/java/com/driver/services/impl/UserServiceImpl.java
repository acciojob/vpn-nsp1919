package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;

import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;


    @Override
    public User register(String username, String password, String countryName) throws Exception{
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        List<Country> countryList = countryRepository3.findAll();
        String countryCode = "";

        for(Country country : countryList){
            if(country.getCountryName().equals(countryName)){
                user.setOriginalCountry(country);
                countryCode = country.getCode();
                break;
            }
        }

        User user1 = userRepository3.save(user);

        String originalIp = countryCode + "." + user1.getId();

        user.setOriginalIp(originalIp);

        userRepository3.save(user);

        return user;
    }


    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        User user=userRepository3.findById(userId).get();
        ServiceProvider serviceProvider=serviceProviderRepository3.findById(serviceProviderId).get();

        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);

        userRepository3.save(user);

        return user;
    }
}
