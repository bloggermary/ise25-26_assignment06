package de.seuhd.campuscoffee.api.mapper;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.models.User;

import java.util.Objects;

public class UserDtoMapper {

    public static UserDto toDto(User user) {
        Objects.requireNonNull(user);

        return UserDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .loginName(user.getLoginName())
                .emailAddress(user.getEmailAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static User fromDto(UserDto dto) {
        Objects.requireNonNull(dto);

        return new User(
                dto.id(),
                dto.createdAt(),
                dto.updatedAt(),
                dto.loginName(),
                dto.emailAddress(),
                dto.firstName(),
                dto.lastName()
        );
    }
}