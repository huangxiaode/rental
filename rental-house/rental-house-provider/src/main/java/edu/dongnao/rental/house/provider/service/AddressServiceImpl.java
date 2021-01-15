package edu.dongnao.rental.house.provider.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.dubbo.config.annotation.Service;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.dongnao.rental.house.api.IAddressService;
import edu.dongnao.rental.house.domain.AreaLevel;
import edu.dongnao.rental.house.domain.SubwayDTO;
import edu.dongnao.rental.house.domain.SubwayStationDTO;
import edu.dongnao.rental.house.domain.SupportAddressDTO;
import edu.dongnao.rental.house.provider.entity.Subway;
import edu.dongnao.rental.house.provider.entity.SubwayStation;
import edu.dongnao.rental.house.provider.entity.SupportAddress;
import edu.dongnao.rental.house.provider.repository.SubwayRepository;
import edu.dongnao.rental.house.provider.repository.SubwayStationRepository;
import edu.dongnao.rental.house.provider.repository.SupportAddressRepository;
import edu.dongnao.rental.lang.ServiceMultiResult;
import edu.dongnao.rental.lang.ServiceResult;

/**
 * 房源区域服务实现类
 */
@Service(protocol = "dubbo")
public class AddressServiceImpl implements IAddressService {
	private static final Logger logger = LoggerFactory.getLogger(IAddressService.class);
	
    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private SubwayRepository subwayRepository;

    @Autowired
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(AreaLevel.CITY.getValue());
        List<SupportAddressDTO> addressDTOS = new ArrayList<>();
        for (SupportAddress supportAddress : addresses) {
            SupportAddressDTO target = modelMapper.map(supportAddress, SupportAddressDTO.class);
            addressDTOS.add(target);
        }
        return new ServiceMultiResult<>(addressDTOS.size(), addressDTOS);
    }

    @Override
    public Map<AreaLevel, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName) {
        Map<AreaLevel, SupportAddressDTO> result = new HashMap<>();
        logger.debug("{}城市{}区域街道查询", cityEnName, regionEnName);
        SupportAddress city = supportAddressRepository.findByEnNameAndLevel(cityEnName, AreaLevel.CITY
                .getValue());
        SupportAddress region = supportAddressRepository.findByEnNameAndBelongTo(regionEnName, city.getEnName());

        result.put(AreaLevel.CITY, modelMapper.map(city, SupportAddressDTO.class));
        result.put(AreaLevel.REGION, modelMapper.map(region, SupportAddressDTO.class));
        return result;
    }

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityName) {
        if (cityName == null) {
            return new ServiceMultiResult<>(0, null);
        }

        ArrayList<SupportAddressDTO> result = new ArrayList<>();

        List<SupportAddress> regions = supportAddressRepository.findAllByLevelAndBelongTo(AreaLevel.REGION
                .getValue(), cityName);
        for (SupportAddress region : regions) {
            result.add(modelMapper.map(region, SupportAddressDTO.class));
        }
        return new ServiceMultiResult<>(regions.size(), result);
    }

    @Override
    public List<SubwayDTO> findAllSubwayByCity(String cityEnName) {
        List<SubwayDTO> result = new ArrayList<>();
        List<Subway> subways = subwayRepository.findAllByCityEnName(cityEnName);
        if (subways.isEmpty()) {
            return result;
        }

        subways.forEach(subway -> result.add(modelMapper.map(subway, SubwayDTO.class)));
        return result;
    }

    @Override
    public List<SubwayStationDTO> findAllStationBySubway(Long subwayId) {
        List<SubwayStationDTO> result = new ArrayList<>();
        List<SubwayStation> stations = subwayStationRepository.findAllBySubwayId(subwayId);
        if (stations.isEmpty()) {
            return result;
        }

        stations.forEach(station -> result.add(modelMapper.map(station, SubwayStationDTO.class)));
        return result;
    }

    @Override
    public ServiceResult<SubwayDTO> findSubway(Long subwayId) {
        if (subwayId == null) {
            return ServiceResult.notFound();
        }
        Optional<Subway> subway = subwayRepository.findById(subwayId);
        if (! subway.isPresent()) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(modelMapper.map(subway.get(), SubwayDTO.class));
    }

    @Override
    public ServiceResult<SubwayStationDTO> findSubwayStation(Long stationId) {
        if (stationId == null) {
            return ServiceResult.notFound();
        }
        Optional<SubwayStation> station = subwayStationRepository.findById(stationId);
        if (! station.isPresent()) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(modelMapper.map(station.get(), SubwayStationDTO.class));
    }

    @Override
    public ServiceResult<SupportAddressDTO> findCity(String cityEnName) {
        if (cityEnName == null) {
            return ServiceResult.notFound();
        }

        SupportAddress supportAddress = supportAddressRepository.findByEnNameAndLevel(cityEnName, AreaLevel.CITY.getValue());
        if (supportAddress == null) {
            return ServiceResult.notFound();
        }

        SupportAddressDTO addressDTO = modelMapper.map(supportAddress, SupportAddressDTO.class);
        return ServiceResult.of(addressDTO);
    }
}
