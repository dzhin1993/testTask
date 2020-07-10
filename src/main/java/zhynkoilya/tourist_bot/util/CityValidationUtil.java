package zhynkoilya.tourist_bot.util;

import zhynkoilya.tourist_bot.model.City;

import java.util.Optional;

public class CityValidationUtil {

    private CityValidationUtil() {
    }

    public static <T> T checkFoundOptional(Optional<T> optional, int id) {
        checkNotFoundWithId(optional.isPresent(), id);
        return optional.get();
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }


    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found city with " + msg);
        }
    }

    public static void checkNew(City city) {
        if (!city.isNew()) {
            throw new IllegalArgumentException(city + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(City entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}