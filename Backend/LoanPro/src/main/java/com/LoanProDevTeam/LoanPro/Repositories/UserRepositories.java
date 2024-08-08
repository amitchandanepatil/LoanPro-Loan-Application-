package com.LoanProDevTeam.LoanPro.Repositories;

import com.LoanProDevTeam.LoanPro.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User,Integer> {
       Optional<User> findByUserUuid(String uuid);

       Optional<User> findByEmail(String email);

       Optional<User> findByPhoneNo(String phoneNo);

       List<User> findByNameContaining(String keywords);

}
