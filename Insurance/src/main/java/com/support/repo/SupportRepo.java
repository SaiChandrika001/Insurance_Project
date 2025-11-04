package com.support.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.support.entity.SupportTicket;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepo extends JpaRepository<SupportTicket, Long>
{

}
