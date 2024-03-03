package by.test.task.security;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Role types for users",
        enumAsRef = true,
        description = """
                Role types:
                * `ADMIN`  - administrator
                * `USER`   - regular user"""
)
public enum UserRole {
    ADMIN, USER
}
