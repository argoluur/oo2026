package ee.argo.filmid.repository;

import ee.argo.filmid.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Long id(Long id);
}
