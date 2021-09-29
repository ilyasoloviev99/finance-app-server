package net.proselyte.jwtappdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.proselyte.jwtappdemo.dto.ObjectToTotalOperations;
import net.proselyte.jwtappdemo.model.Operation;
import net.proselyte.jwtappdemo.model.User;
import net.proselyte.jwtappdemo.repository.OperationRepository;
import net.proselyte.jwtappdemo.repository.UserRepository;
import net.proselyte.jwtappdemo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;
    private UserRepository userRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository, UserRepository userRepository) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Operation> getAll() {
        List<Operation> result = operationRepository.findAll();
        log.info("IN getAll - {} operations found", result.size());
        return result;
    }

    @Override
    public Operation findById(Long id) {
        Operation result = operationRepository.findById(id).orElse(null);
        if (result == null){
            log.warn("IN findById - no operation found by id: {}", id);
            return null;
        }
        log.info("IN findById - operation: {} found by id: {}", result);
        return result;

    }

    @Override
    public List<Operation> findByUserId(Long id) {
        List<Operation> result = operationRepository.findByUserId(id);
        log.info("IN getByUserId - {} operation found with id: {}", result.size(),id);
        return result;
    }

    @Override
    public Operation create(Operation operation, Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);

        if (user == null) {
            log.warn("IN findById - no user found by id: {}", idUser);
            return null;
        }
        operation.setUser(user);
        operationRepository.save(operation);
        log.info("IN create - operation: {} successfully created", operation);
        return operation;
    }

    @Override
    public Operation update(Operation operation, Long id, User user) {
        operation.setId(id);
        operation.setUser(user);
        operationRepository.save(operation);
        log.info("IN update - operation: {} successfully updated", operation);
        return operation;
    }

    @Override
    public void delete(Long id) {
        operationRepository.deleteById(id);
    }



}