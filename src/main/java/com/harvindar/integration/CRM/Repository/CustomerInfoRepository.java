package com.harvindar.integration.CRM.Repository;

import com.harvindar.integration.CRM.Data.CustomerInfoData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfoData, Long> {

    Optional<CustomerInfoData> findByEmail(String email);
}