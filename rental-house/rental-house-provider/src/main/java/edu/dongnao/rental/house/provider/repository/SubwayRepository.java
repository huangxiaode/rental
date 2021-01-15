package edu.dongnao.rental.house.provider.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.dongnao.rental.house.provider.entity.Subway;

/**
 * 地铁数据访问接口
 */
public interface SubwayRepository extends CrudRepository<Subway, Long>{
    List<Subway> findAllByCityEnName(String cityEnName);
}
