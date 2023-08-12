package project.socialNetwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.socialNetwork.dto.UserDto;
import project.socialNetwork.model.entity.UserEntity;
import project.socialNetwork.service.UserService;
import project.socialNetwork.utils.mappingDto.MappingUserDtoUtils;

import java.security.Principal;

@RestController
@RequestMapping("api/secured/users/")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final MappingUserDtoUtils mappingUserDtoUtils;

    @Autowired
    public UserController(UserService userService, MappingUserDtoUtils mappingUserDtoUtils) {
        this.userService = userService;
        this.mappingUserDtoUtils = mappingUserDtoUtils;
    }

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        UserEntity userEntity = userService.getCurrentUser(principal);
        UserDto userDTO = mappingUserDtoUtils.mapToUserDto(userEntity);
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }
}
