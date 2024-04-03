package peaksoft.appplaza.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza.model.entities.Application;
import peaksoft.appplaza.model.enums.Status;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT app FROM Application  app JOIN app.users user where user.id=:id")
    public List<Application> myGetApplicationsByUserId(@Param("id") Long userId);

    @Query("SELECT app FROM Application  app WHERE UPPER(app.name)LIKE CONCAT('%',:text,'%')OR UPPER(app.GenreName)LIKE CONCAT('%',:text,'%') ")
    List<Application> searchApplication(@Param("text") String text, Pageable pageable);
    @Query("SELECT  app from Application  app WHERE app.status ='BASIC'")
  List<Application>findAllByStatusBasic();
}
