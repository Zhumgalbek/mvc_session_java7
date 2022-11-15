package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.House;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HouseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<House> getAllHouse() {
        return entityManager
                .createQuery("select h from House h", House.class)
                .getResultList();
    }

    public House getHouseById(Long id) {
        return entityManager.find(House.class, id);
    }

    public void saveHouse(House house) {
        entityManager.persist(house);
    }

    public void deleteHouseById(Long id) {
        entityManager.remove(entityManager.find(House.class, id));
    }
}
