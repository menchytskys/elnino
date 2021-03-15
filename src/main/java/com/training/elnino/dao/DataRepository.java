package com.training.elnino.dao;

import com.training.elnino.model.DataRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<DataRow, Long> {
    }
