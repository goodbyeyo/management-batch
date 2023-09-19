package com.study.batch.repository.packaze;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    public void test_findByCreatedAtAfter() {
        // given
        PackageEntity packageEntity1 = new PackageEntity();
        packageEntity1.setPackageName("학생 전용 운동 2개월 프로그램");
        packageEntity1.setPeriod(60);
        packageRepository.save(packageEntity1);

        PackageEntity packageEntity2 = new PackageEntity();
        packageEntity2.setPackageName("학생 전용 운동 4개월 프로그램");
        packageEntity2.setPeriod(120);
        packageRepository.save(packageEntity2);

        // when
        final List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(
                LocalDateTime.now().minusMinutes(1),
                PageRequest.of(0, 1, Sort.by("packageSeq").descending())
        );

        // then
        assertEquals(1, packageEntities.size());
        assertEquals(packageEntity2.getPackageSeq(), packageEntities.get(0).getPackageSeq());
    }

    @Test
    public void test_updateCountAndPeriod() {
        // given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디 프로필 이벤트 3개월");
        packageEntity.setPeriod(90);
        packageRepository.save(packageEntity);

        // when
        int updateCount = packageRepository.updateCountAndPeriod(packageEntity.getPackageSeq(), 30, 120);
        final PackageEntity updatedPackageEntity = packageRepository.findById(packageEntity.getPackageSeq()).get();

        // then
        assertEquals(1, updateCount);
        assertEquals(30, updatedPackageEntity.getCount());
        assertEquals(120, updatedPackageEntity.getPeriod());
    }

    @Test
    public void test_delete() {
        // given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("이용권 제거");
        packageEntity.setCount(1);
        PackageEntity newPackageEntity = packageRepository.save(packageEntity);

        // when
        packageRepository.deleteById(newPackageEntity.getPackageSeq());

        assertTrue(packageRepository.findById(newPackageEntity.getPackageSeq()).isEmpty());


    }
}
