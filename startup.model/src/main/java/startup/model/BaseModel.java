package startup.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;

@MappedSuperclass
@FilterDef(name = "notDeleted")
@Filters({ @Filter(name = "notDeleted", condition = "deletedDate IS NULL") })
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private final String guid = UUID.randomUUID().toString();

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime  modifiedDate;

    @Column
    private LocalDateTime  deletedDate;

    @Column
    private String deletedBy;

    public BaseModel() {
        super();
        this.prePersist();
    }

    public Integer getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public LocalDateTime getDeletedDate() {
        return this.deletedDate;
    }

    public void setDeletedDate(final LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return this.deletedBy;
    }

    public void setDeletedBy(final String deletedBy) {
        this.deletedBy = deletedBy;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

}
