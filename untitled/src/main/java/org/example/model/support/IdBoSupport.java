package org.example.model.support;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class IdBoSupport {
    private long createdOn = System.currentTimeMillis();
    private long updatedOn = System.currentTimeMillis();
    private boolean isDeleted = false;
    private long deletedOn;
}
