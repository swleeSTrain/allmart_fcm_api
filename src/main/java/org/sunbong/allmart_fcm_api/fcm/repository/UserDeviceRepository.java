package org.sunbong.allmart_fcm_api.fcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sunbong.allmart_fcm_api.fcm.domain.UserDevice;

import java.util.Optional;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
    Optional<UserDevice> findByUserIdAndMartId(Long userId, Long martId);
}