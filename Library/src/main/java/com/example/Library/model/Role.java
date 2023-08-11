package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private Collection<RoleScreenPermission> roleScreenPermissions;
}
