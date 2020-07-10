package zhynkoilya.tourist_bot.util;

import lombok.experimental.UtilityClass;
import zhynkoilya.tourist_bot.model.City;
import zhynkoilya.tourist_bot.util.exception.NotFoundException;

import java.util.Optional;

@UtilityClass
public class CityValidationUtil {

    public City checkFoundOptional(Optional<City> optional, int id) {
        checkNotFoundWithId(optional.isPresent(), id);
        return optional.get();
    }

    public void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found city with " + msg);
        }
    }

    public void checkNew(City city) {
        if (!city.isNew()) {
            throw new IllegalArgumentException(city + " must be new (id=null)");
        }
    }

    public void assureIdConsistent(City city, int id) {
        if (city.isNew()) {
            city.setId(id);
        } else if (city.getId() != id) {
            throw new IllegalArgumentException(city + " must be with id=" + id);
        }
    }
}