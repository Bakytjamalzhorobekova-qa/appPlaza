package peaksoft.appplaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.appplaza.model.entities.InfoUser;

public interface InfoUserRepository extends JpaRepository<InfoUser,Long> {
    
}
