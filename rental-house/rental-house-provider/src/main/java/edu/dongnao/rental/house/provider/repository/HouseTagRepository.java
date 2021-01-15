package edu.dongnao.rental.house.provider.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.dongnao.rental.house.provider.entity.HouseTag;

/**
 * 房源标签数据访问层
 */
public interface HouseTagRepository extends CrudRepository<HouseTag, Long> {
    HouseTag findByNameAndHouseId(String name, Long houseId);

    List<HouseTag> findAllByHouseId(Long id);

    List<HouseTag> findAllByHouseIdIn(List<Long> houseIds);
}
