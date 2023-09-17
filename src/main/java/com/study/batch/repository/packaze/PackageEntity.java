package com.study.batch.repository.packaze;

import com.study.batch.repository.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "package")
public class PackageEntity extends BaseEntity {

    @Id                                                     // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK 생성 규칙(전략) 지정 : IDENTITY 전략은 DB에 위임
    private Integer packageSeq;

    private String packageName;
    private Integer count;
    private Integer period;

}
