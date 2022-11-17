package com.example.intermediate.entity;

import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

// Java 진영에서는 상속 관계이다. 하지만
// MappedSupperclass를 작성하여 RDB 진영에는 상속관계가 아님을 표시한다.
// 각 필드를 개별적으로 자주 사용하거나 바로 접근해야 할 대에는 지금처럼 @MappedSupperclass 방식을 사용한다.
@MappedSuperclass   // 모듈화
@Getter @EntityListeners(AuditingEntityListener.class)
public abstract class Period {
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

    /*@PrePersist
    public void created(){
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void updated(){
        this.updatedDate = LocalDateTime.now();
    }*/
}
