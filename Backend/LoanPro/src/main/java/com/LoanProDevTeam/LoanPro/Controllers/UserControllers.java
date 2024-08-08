package com.LoanProDevTeam.LoanPro.Controllers;

import com.LoanProDevTeam.LoanPro.Dtos.ApiResponseMessage;
import com.LoanProDevTeam.LoanPro.Dtos.ImageResponse;
import com.LoanProDevTeam.LoanPro.Dtos.PageableResponse;
import com.LoanProDevTeam.LoanPro.Dtos.UserDto;
import com.LoanProDevTeam.LoanPro.Services.FileService;
import com.LoanProDevTeam.LoanPro.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    private UserService userService;
    Logger logger= LoggerFactory.getLogger(UserControllers.class);

    @Autowired
    private FileService fileServices;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        logger.info("create user controller");
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userUuid}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userUuid,@Valid @RequestBody UserDto userDto){
        logger.info("update user controller");
        UserDto user = userService.updateUser(userDto, userUuid);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userUuid}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userUuid") String userUuid){
        logger.info("delete user controller");
        userService.deleteUser(userUuid);
        ApiResponseMessage userDeletedSuccessfully = ApiResponseMessage.builder()
                .message("user deleted successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(userDeletedSuccessfully,HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber" ,defaultValue = "0" , required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10" , required = false) int pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = "name" , required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc" , required = false) String sortDir
    ){
        logger.info("get all user controller");
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    @GetMapping("/{userUuid}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userUuid){
        logger.info("get user controller");
        return new ResponseEntity<>(userService.getUserByUuid(userUuid),HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        logger.info("get user by email controller");
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword){
        logger.info("search user controller");
        return new ResponseEntity<>(userService.searchUsers(keyword),HttpStatus.OK);
    }

    @PostMapping("/image/{userUuid}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image,
                                                         @PathVariable String userUuid) throws IOException {
       String imageName=fileServices.uploadFile(image,imageUploadPath);
       UserDto user = userService.getUserByUuid(userUuid);
       user.setImageName(imageName);
       userService.updateUser(user,userUuid);
       ImageResponse imageResponse = ImageResponse
                .builder()
                .imageName(imageName)
                .success(true)
                .message("image is uploaded successfully ")
                .status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }

    // serve user image
   @GetMapping(value = "/image/{userUuid}")
   public void serveUserImage(@PathVariable String userUuid, HttpServletResponse response) throws IOException {
        UserDto user = userService.getUserByUuid(userUuid);
        InputStream resource = fileServices.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }

}
