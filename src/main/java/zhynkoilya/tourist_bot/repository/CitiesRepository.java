package zhynkoilya.tourist_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zhynkoilya.tourist_bot.model.City;

import java.util.List;

import static zhynkoilya.tourist_bot.util.CityValidationUtil.*;

@Repository
public interface CitiesRepository extends JpaRepository<City, Integer> {

    @Override
    List<City> findAll();

    default City get(int id) {
        return checkFoundOptional(findById(id), id);
    }

    default void delete(int id) {
        checkNotFoundWithId(deleteById(id) != 0, id);
    }

    @Modifying
    @Transactional
    @Query("DELETE FROM City c WHERE c.id=:id")
    int deleteById(@Param("id") int id);

    @Transactional
    default City create(City city) {
        checkNew(city);
        return save(city);
    }

    @Transactional
    default void update(City city, int id) {
        assureIdConsistent(city, id);
        save(city);
    }
}
