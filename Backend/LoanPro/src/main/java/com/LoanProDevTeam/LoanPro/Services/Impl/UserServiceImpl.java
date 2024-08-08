package com.LoanProDevTeam.LoanPro.Services.Impl;

import com.LoanProDevTeam.LoanPro.Dtos.PageableResponse;
import com.LoanProDevTeam.LoanPro.Dtos.UserDto;
import com.LoanProDevTeam.LoanPro.Entities.User;
import com.LoanProDevTeam.LoanPro.Exceptions.ResourceNotFoundException;
import com.LoanProDevTeam.LoanPro.Repositories.UserRepositories;
import com.LoanProDevTeam.LoanPro.Services.UserService;
import com.LoanProDevTeam.LoanPro.helper.Helper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private ModelMapper mapper;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("entered into create service");
        User user = mapper.map(userDto, User.class);
        user.setUserUuid(UUID.randomUUID().toString());
        user.setCreatedBy(userDto.getUserUuid());
        user.setCreatedDate(new Date());
        User save = userRepositories.save(user);
        return mapper.map(save, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String uuid) {
        logger.info("entered into create user service:{}",uuid);
        User user = userRepositories.findByUserUuid(uuid).orElseThrow(() -> new ResourceNotFoundException("user not found with given UUID"));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setAge(userDto.getAge());
        user.setImageName(userDto.getImageName());
        user.setUpdatedBy(userDto.getUserUuid());
        user.setUpdatedDate(new Date());

        User save = userRepositories.save(user);
        return mapper.map(save, UserDto.class);

    }

    @Override
    public void deleteUser(String uuid) {
        logger.info("entered into deleteUser service:{}",uuid);
        User user = userRepositories.findByUserUuid(uuid).orElseThrow(() -> new ResourceNotFoundException("user not found with given UUID"));
        userRepositories.delete(user);
    }

    @Override
    public UserDto getUserByUuid(String uuid) {
        logger.info("entered into getUserByUuid service:{}",uuid);
        User user = userRepositories.findByUserUuid(uuid).orElseThrow(() -> new ResourceNotFoundException("user not found with given UUID"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        logger.info("entered into getUserByEmail service:{}",email);
        User user = userRepositories.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found with given email" + email));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByMobileNumber(String mobileNumber) {
        logger.info("entered into getUserByMobileNumber service:{}",mobileNumber);
        User user = userRepositories.findByPhoneNo(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("user not found with given email" +mobileNumber ));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> searchUsers(String keyword) {
        logger.info("entered into searchUsers service:{}",keyword);
        List<User> users = userRepositories.findByNameContaining(keyword);

        return users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }


    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("entered into getAllUser service");
        //Create a Sort object based on the sort direction and sort by field
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending())
                  :(Sort.by(sortBy));
        // The above line is a ternary operator. It checks if sortDir is "desc". If true, it creates a descending sort by the sortBy field;
        // otherwise, it creates an ascending sort by the sortBy field.

        // Create a Pageable object for pagination and sorting
        // PageRequest.of creates a new PageRequest with the given page number, page size, and sort.
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Retrieve a page of User records from the repository using the created Pageable
        // The userRepository.findAll method fetches a page of User records based on the provided pagination and sorting criteria.
        Page<User> page = userRepositories.findAll(pageable);

        // Convert the fetched Page<User> to a PageableResponse<UserDto> using a helper method
        // Helper.getPageableResponse method presumably maps User entities to UserDto DTOs and creates a PageableResponse.
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class);

        return pageableResponse;
    }

}
