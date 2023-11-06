package br.com.fiap.apitask.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.apitask.models.UserData;


import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);
}
