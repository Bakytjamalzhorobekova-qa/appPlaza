package peaksoft.appplaza.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza.model.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.email=:email")
    Optional<User> findByEmail(@Param("email") String email);


    @Query("SELECT  user FROM User user WHERE  UPPER(user.name) LIKE  CONCAT('%',:text,'%') OR " +
           "UPPER(user.lastName) LIKE CONCAT('%',:text,'%') OR  UPPER(user.email)LIKE CONCAT('%',:text,'%')")
    List<User> searchAndPagination(@Param("text") String text, Pageable pageable);
}
