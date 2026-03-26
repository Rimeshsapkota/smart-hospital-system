package com.smarthospital.authservice.auth.entity;

import com.smarthospital.common_lib.entity.BaseEntity;
import com.smarthospital.common_lib.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String forgetPasswordCode;
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getRole(){
        return role.name();
    }
    public Collection<? extends GrantedAuthority> getRoles() {
        return new ArrayList<>() {{
            add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return getRole();
                }
            });
        }};
    }

}
