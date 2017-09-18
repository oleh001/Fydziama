package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
