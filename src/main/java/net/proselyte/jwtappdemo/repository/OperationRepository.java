package net.proselyte.jwtappdemo.repository;


import net.proselyte.jwtappdemo.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByUserId(Long id);

    List<Operation> findByUserUsername(String username);
}
