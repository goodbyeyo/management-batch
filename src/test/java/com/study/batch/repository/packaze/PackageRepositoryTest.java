package com.study.batch.repository.packaze;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class PackageRepositoryTest {

    @Autowired
    private PackageRepository packageRepository;

    @Test
    public void test_save() {
        // given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("건강 다이어트 운동 4주 프로그램");
        packageEntity.setPeriod(30);

        // when
        packageRepository.save(packageEntity);

        // then
        assertNotNull(packageEntity.getPackageSeq());
        assertEquals("건강 다이어트 운동 4주 프로그램", packageEntity.getPackageName());
    }

}