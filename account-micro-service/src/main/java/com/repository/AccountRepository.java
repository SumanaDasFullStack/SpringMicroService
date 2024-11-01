package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

	@Query("select acc.amount from AccountEntity acc where acc.emailid=:emailid")
	public float findBalanceByEmailId(@Param("emailid") String email);
}
