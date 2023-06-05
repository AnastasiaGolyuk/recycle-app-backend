package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.Role;
import com.bsuir.diploma.recycleappbackend.model.entity.Status;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Интерфейс, отвечающий за взаимодействие моделей с БД
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Найти пользователя по электронной почте.
     *
     * @param email электронная почта пользователя
     * @return Optional, содержащий найденного пользователя
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Проверить существование пользователя по электронной почте.
     *
     * @param email электронная почта пользователя
     * @return true, если пользователь с указанной электронной почтой существует, иначе false
     */
    boolean existsByEmail(String email);

    /**
     * Обновить пароль пользователя по заданному идентификатору.
     *
     * @param id       идентификатор пользователя
     * @param password новый пароль
     */
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(Long id, String password);

    /**
     * Обновить статус пользователя по заданному идентификатору.
     *
     * @param id     идентификатор пользователя
     * @param status новый статус
     */
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void updateStatusById(Long id, Status status);

//    int countAllByRoleEquals(Role role);

}
