package peaksoft.appplaza.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza.model.entities.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("SELECT genre FROM Genre genre where upper(genre.name) like CONCAT('%',:text,'%')")
    List<Genre> searchAndPagination(@Param("text") String text, Pageable pageable);


}
