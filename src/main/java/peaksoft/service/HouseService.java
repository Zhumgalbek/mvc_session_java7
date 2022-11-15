package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.House;
import peaksoft.repository.HouseRepository;

import java.util.List;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> getAllHouse() {
        return houseRepository.getAllHouse();
    }

    public House getHouseById(Long id) {
        return houseRepository.getHouseById(id);
    }

    public void saveHouse(House house) {
        houseRepository.saveHouse(house);
    }

    public void deleteHouseById(Long id) {
        houseRepository.deleteHouseById(id);
    }
}
