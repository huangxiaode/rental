package edu.dongnao.rental.house.provider.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.dongnao.rental.house.provider.entity.SubwayStation;

/**
 * 地铁站台数据访问接口
 */
public interface SubwayStationRepository extends CrudRepository<SubwayStation, Long> {
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
