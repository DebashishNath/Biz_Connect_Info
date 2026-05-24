package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// CLIENT REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByMobileNo(String mobileNo);

    List<Client> findByClientType(String clientType);

    List<Client> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Client> findByContactPersonNameContainingIgnoreCase(String contactPersonName);

    List<Client> findByCityCityId(Integer cityId);

    List<Client> findByCreatedById(Long userId);

    List<Client> findTop10ByOrderByCreatedAtDesc();
}