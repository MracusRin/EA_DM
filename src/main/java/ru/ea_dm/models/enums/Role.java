package ru.ea_dm.models.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Role implements GrantedAuthority {
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public final static Map<String, Role> roles = Arrays.stream(Role.values())
            .collect(Collectors.toMap(r -> r.roleName, v -> v));

    public String getRoleName() {
        return this.roleName;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
