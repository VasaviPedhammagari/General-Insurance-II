package com.lti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.entity.User;

@Repository
public interface AdminViewDetailsDao extends JpaRepository<User, Integer> {

}
