package de.seuhd.campuscoffee.domain.model;

import lombok.Builder;
import org.jspecify.annotations.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
public record User(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        @NonNull String loginName,
        @NonNull String emailAddress,
        @NonNull String firstName,
        @NonNull String lastName
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Java record compact constructor — runs AFTER field assignment
    public User {
        // Static null checks required by assignment
        Objects.requireNonNull(loginName, "loginName must not be null");
        Objects.requireNonNull(emailAddress, "emailAddress must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
    }
}