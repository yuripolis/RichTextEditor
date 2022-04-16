package texteditor.texteditor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import texteditor.texteditor.model.Post;

@Repository
public interface campeonatoRepository extends JpaRepository<Post, Long> {
}
