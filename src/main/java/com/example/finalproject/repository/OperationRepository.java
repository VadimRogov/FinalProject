package com.example.finalproject.repository;

import com.example.finalproject.entity.BaseOfOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<BaseOfOperation, Long> {

    @Query("SELECT b FROM BaseOfOperation b WHERE b.user = :id AND b.timeOperation >= :beginDate AND b.timeOperation <= :endDate")
    List<BaseOfOperation> findOperationsByUserIdAndDateRange(@Param("id") long id,
                                                       @Param("beginDate") Date beginDate,
                                                       @Param("endDate") Date endDate);

    @Query("SELECT b FROM BaseOfOperation b WHERE b.user = :id")
    List<BaseOfOperation> findOperationsByUserId(@Param("id") long id);
}
