package com.serheev.model;

import com.serheev.annotation.InjectRandomValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "developer")
@NamedQuery(name = "DeveloperEntity.getAll", query = "SELECT d FROM DeveloperEntity d")
public class DeveloperEntity extends BaseEntity {
    private static Logger log = Logger.getLogger(DeveloperEntity.class);

    @InjectRandomValue(text = "John Connor")
    @Column(name = "name", length = 64)
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "sex")
    private char sex;
    @InjectRandomValue(min = 100, max = 1000)
    @Column(name = "salary")
    private int salary;
    @InjectRandomValue(flag = true)
    @Column(name = "onleave")
    private boolean onLeave;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "developers")
    private Set<CompanyEntity> companies;

    @ManyToMany
    @JoinTable(name = "developer_project",
            joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private Set<ProjectEntity> projects;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    private Set<SkillEntity> skills;

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
