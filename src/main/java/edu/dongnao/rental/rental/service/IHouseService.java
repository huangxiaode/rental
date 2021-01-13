package edu.dongnao.rental.rental.service;

import edu.dongnao.rental.rental.entity.House;

/**
 * 房源服务类
 * 
 *
 */
public interface IHouseService {

	House save(House house);

	House findById(Long id);
}
