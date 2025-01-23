package com.dating.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dating.Entity.Gender;
import com.dating.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.gender <> :gender AND u.id <> :id")
	List<User> findAllByGenderNotAndIdNot(@Param("gender") Gender gender, @Param("id") Long id);

}
