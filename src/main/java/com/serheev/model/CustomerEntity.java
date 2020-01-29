package com.serheev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@NamedQuery(name = "CustomerEntity.getAll", query = "SELECT c FROM CustomerEntity c")
public class CustomerEntity extends BaseEntity {
    private static Logger log = Logger.getLogger(CustomerEntity.class);

    @Column(name = "name", length = 128)
    private String name;
    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id", unique = true, nullable = false, updatable = false, insertable = false)
    private ProjectEntity projects;

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
