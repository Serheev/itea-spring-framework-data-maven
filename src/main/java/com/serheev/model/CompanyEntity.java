package com.serheev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "country"})})
@NamedQuery(name = "CompanyEntity.getAll", query = "SELECT c FROM CompanyEntity c")
public class CompanyEntity extends BaseEntity {
    private static Logger log = Logger.getLogger(CompanyEntity.class);

    @NonNull
    @Column(name = "name", length = 64)
    private String name;
    @NonNull
    @Column(name = "country", length = 32)
    private String country;

    @ManyToMany
    @JoinTable(name = "company_developer",
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id")
    )
    private Set<DeveloperEntity> developers;

    @PrePersist
    public void callbackPrePersist() {
        log.info("+++ Executed a PrePersist callback method!");
    }

    @PostPersist
    public void callbackPostPersist() {
        log.info("+++ Executed a PostPersist callback method!");
    }

    @PreUpdate
    public void callbackPreUpdate() {
        log.info("+++ Executed a PreUpdate callback method!");
    }

    @PostUpdate
    public void callbackPostUpdate() {
        log.info("+++ Executed a PostUpdate callback method!");
    }

    @PostLoad
    public void callbackPostLoad() {
        log.info("+++ Executed a PostLoad callback method!");
    }

    @PreRemove
    public void callbackPreRemove() {
        log.info("+++ Executed a PreRemove callback method!");
    }

}
