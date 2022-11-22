package com.example.demo.Repository;

import com.example.demo.Entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository extends JpaRepository<Entity, Long> {

    @Query("SELECT u FROM Entity u WHERE u.username=:username")
    public  Entity getuserByUsername(@Param("username") String username);

}
