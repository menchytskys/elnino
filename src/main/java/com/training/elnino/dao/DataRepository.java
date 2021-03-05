package com.training.elnino.dao;

import com.training.elnino.model.DataRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<DataRow, Long> {

    @Override
    Optional<DataRow> findById(Long aLong);

    @Override
    <S extends DataRow> S save(S s);
}
