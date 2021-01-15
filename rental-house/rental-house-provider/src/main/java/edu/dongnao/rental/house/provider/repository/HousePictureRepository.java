package edu.dongnao.rental.house.provider.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.dongnao.rental.house.provider.entity.HousePicture;

/**
 * 房源图片数据访问层
 */
public interface HousePictureRepository extends CrudRepository<HousePicture, Long> {
    List<HousePicture> findAllByHouseId(Long id);
}
