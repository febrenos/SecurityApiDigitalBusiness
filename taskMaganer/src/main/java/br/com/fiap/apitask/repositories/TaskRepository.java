package br.com.fiap.apitask.repositories;

import br.com.fiap.apitask.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByIsActiveTrue(Pageable pageable);
}
