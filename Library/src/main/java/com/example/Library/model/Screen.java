package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Collection;

@Entity
@Table(name = "screen")
@Data @AllArgsConstructor @NoArgsConstructor
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idscreen")
    private Long id;
    private String name;
    @Lob
    private byte[] images;
    private String description;

    @OneToMany(mappedBy = "screen")
    @JsonManagedReference
    private Collection<RoleScreenPermission> roleScreenPermissions;
}
