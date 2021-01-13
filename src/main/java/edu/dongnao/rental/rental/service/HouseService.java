package edu.dongnao.rental.rental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dongnao.rental.rental.dao.HouseRepository;
import edu.dongnao.rental.rental.entity.House;

@Service
public class HouseService implements IHouseService {

	@Autowired
	HouseRepository resitory;

	@Override
	public House save(House house) {
		House data = resitory.save(house);
		return data;
	}

	@Override
	public House findById(Long id) {
		Optional<House> houseOpt = resitory.findById(id);
		/*
		 * if(houseOpt.isPresent()) { return houseOpt.get(); } throw new
		 * NullPointerException("没有找到数据");
		 */
		return houseOpt.get();
	}

}
