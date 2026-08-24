package com.ecommerce.user.services;

import com.ecommerce.user.dto.AddressDto;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private List<User> userList=new ArrayList<>();

    public List<UserResponse> fetchAllUsers()
    {
        List<User> userList=userRepository.findAll();

        return userRepository.findAll().stream().map(
                this::mapToUserResponse)
                .collect(Collectors.toList());

    }

    public Optional<UserResponse> fetchUser(Long id)
    {
   return  userRepository.findById(id)
           .map(this::mapToUserResponse);
    }

    public void addUser(UserRequest userRequest)
    {
          User user=new User();
          updateUserFromRequest(user,userRequest);
         userRepository.save(user);
    }

    public void updateUser(Long id, UserRequest updatedUserRequest) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateUserFromRequest(existingUser, updatedUserRequest);

        userRepository.save(existingUser);
    }
    public void patchUser(Long id, UserRequest updatedUserRequest) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateUserFromRequest(existingUser, updatedUserRequest);

        userRepository.save(existingUser);
    }

    private void updateUserFromRequest(User user,UserRequest userRequest){



        if (userRequest.getFirstName() != null) user.setFirstName(userRequest.getFirstName());
        if (userRequest.getLastName() != null) user.setLastName(userRequest.getLastName());

        if (userRequest.getEmail() != null) user.setEmail(userRequest.getEmail());
        if (userRequest.getPhone() != null) user.setPhone(userRequest.getPhone());
        if (userRequest.getRole() != null) user.setRole(userRequest.getRole());


        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipCode(userRequest.getAddress().getZipCode());
            address.setPostalCode(userRequest.getAddress().getPostalCode());
            user.setAddress(address);
        }

    }
    private UserResponse mapToUserResponse(User user)
    {
        UserResponse response=new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());


        if (user.getAddress() != null)

        {
            AddressDto addressDto = new AddressDto();
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setZipCode(user.getAddress().getZipCode());
            addressDto.setPostalCode(user.getAddress().getPostalCode());
             response.setAddress((addressDto));


        }

        return  response;

    }




}
