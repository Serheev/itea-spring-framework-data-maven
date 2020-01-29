package com.serheev.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Map;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users", schema = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "password"})})
@NamedQuery(name = "UserEntity.getAll", query = "SELECT u FROM UserEntity u")
public class UserEntity extends BaseEntity{
    @NonNull
    @Column(name = "name", length = 64)
    private String name;
    @NonNull
    @Column(name = "password", length = 64)
    private String password;
    @Type(type = "jsonb")
    @Column(name="additional_info", columnDefinition = "jsonb")
    private Map<String, Object> additionalInfo;
}
