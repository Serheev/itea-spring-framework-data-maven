package com.serheev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
@NamedQuery(name = "ProjectEntity.getAll", query = "SELECT p FROM ProjectEntity p")
public class ProjectEntity extends BaseEntity {
    private static Logger log = Logger.getLogger(ProjectEntity.class);

    @Column(name = "name", length = 128)
    private String name;
    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "end_date")
    private Date end_date;
    @Column(name = "cost")
    private int cost;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<DeveloperEntity> developers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<CustomerEntity> customers;

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
