package project.socialNetwork.utils.mappingDto;

import org.springframework.stereotype.Component;
import project.socialNetwork.dto.UserDto;
import project.socialNetwork.model.entity.UserEntity;

@Component
public class MappingUserDtoUtils {

    public UserEntity mapToUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(userDto.getFirstname());
        userEntity.setLastname(userDto.getLastname());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }

    public UserDto mapToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getUsername());
        userDto.setFirstname(userEntity.getFirstname());
        userDto.setLastname(userEntity.getLastname());
        if(userEntity.getRole() != null) {
            userDto.setRole(userEntity.getRole().name());
        }
        return userDto;
    }
}
