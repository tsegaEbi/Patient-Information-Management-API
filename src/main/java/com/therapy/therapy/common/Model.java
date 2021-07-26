package com.therapy.therapy.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Setter
@Getter
@MappedSuperclass
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", name = "id", updatable = false, nullable = false)
    private Long id;

    @Version
    private Long version;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name="date_updated")
    private Date dateUpdated;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name="date_created")
    private Date dateCreated= new Date();


    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof com.therapy.therapy.common.Model))
            return false;
        else
            return this.id == null && ((com.therapy.therapy.common.Model) obj).id == null
                    || (null != this.id && this.id.equals(((com.therapy.therapy.common.Model)obj).id));
    }

    @Override
    public int hashCode() {
        return null != this.id ? this.id.hashCode() : super.hashCode();
    }
}


