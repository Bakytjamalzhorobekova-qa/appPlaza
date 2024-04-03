package peaksoft.appplaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza.model.entities.MailSender;
@Repository
public interface MailRepository extends JpaRepository<MailSender,Long> {
}
