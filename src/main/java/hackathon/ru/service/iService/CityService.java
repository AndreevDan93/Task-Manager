package hackathon.ru.service.cityService;

import hackathon.ru.dto.ApplicationDto;
import hackathon.ru.dto.CityDto;
import hackathon.ru.model.Application;
import hackathon.ru.model.City;

import java.util.List;

public interface CityService {
    City getCityById(Long id);
    List<City> getAllCities();
    City createCity(CityDto cityDto);
    City updateCity(Long id, CityDto cityDto);
    void deleteCity(Long id);
}
