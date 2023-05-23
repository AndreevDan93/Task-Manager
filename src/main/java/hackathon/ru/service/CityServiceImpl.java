package hackathon.ru.service.cityService;

import hackathon.ru.dto.CityDto;
import hackathon.ru.model.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    @Override
    public City getCityById(Long id) {
        return null;
    }

    @Override
    public List<City> getAllCities() {
        return null;
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
    public void deleteCity(Long id) {

    }
}
