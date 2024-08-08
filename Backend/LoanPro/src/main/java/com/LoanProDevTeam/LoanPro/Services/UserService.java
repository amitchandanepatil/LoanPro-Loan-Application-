package com.LoanProDevTeam.LoanPro.Services;


import com.LoanProDevTeam.LoanPro.Dtos.PageableResponse;
import com.LoanProDevTeam.LoanPro.Dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
   UserDto createUser(UserDto userDto);
   UserDto updateUser(UserDto userDto,String uuid);
   void deleteUser(String uuid);
   UserDto getUserByUuid(String uuid);
   UserDto getUserByEmail(String email);
   UserDto getUserByMobileNumber(String mobileNumber);
   List<UserDto> searchUsers(String keyword);
   PageableResponse<UserDto> getAllUser(int pageNumber , int pageSize, String sortBy, String sortDir);

}

