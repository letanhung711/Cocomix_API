package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "permission")
@Data @AllArgsConstructor @NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpermission")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "permission")
    @JsonManagedReference
    private Collection<RoleScreenPermission> roleScreenPermissions;
}
