package zhynkoilya.tourist_bot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zhynkoilya.tourist_bot.model.City;
import zhynkoilya.tourist_bot.repository.CitiesRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Used to manage of cities data through the rest api
 */

@Slf4j
@RestController
@RequestMapping(value = "admin/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class TouristBotRestController {

    private final CitiesRepository repository;

    public TouristBotRestController(CitiesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<City> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public City get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        repository.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid City city, @PathVariable("id") int id) {
        city.setId(id);
        log.info("update {}", city);
        repository.update(city, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public City save(@RequestBody @Valid City city) {
        log.info("create {}", city);
        return repository.create(city);
    }
}
