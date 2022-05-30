package ph.apper;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    List<Animal> findByName(String name);
}
