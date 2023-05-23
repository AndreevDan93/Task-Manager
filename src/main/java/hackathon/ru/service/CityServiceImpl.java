package hackathon.ru.service;

import hackathon.ru.dto.CityDto;
import hackathon.ru.exception.custom.CityNotFoundException;
import hackathon.ru.model.City;
import hackathon.ru.repository.CityRepository;
import hackathon.ru.service.iService.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("city with that id is not found"));
    }

    @Override
    public List<City> getAllCities() {
        return new ArrayList<>(cityRepository.findAll());
    }

    @Override
    public City createCity(CityDto cityDto) {
        return null;
    }

    @Override
    public City updateCity(Long id, CityDto cityDto) {
        return null;
    }

    @Override
    public void deleteCityById(Long id) {

    }
}
