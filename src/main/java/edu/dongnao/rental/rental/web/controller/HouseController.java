package edu.dongnao.rental.rental.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.dongnao.rental.rental.entity.House;
import edu.dongnao.rental.rental.service.IHouseService;
import edu.dongnao.rental.rental.web.dto.HouseDTO;

@RestController
public class HouseController {
	@Autowired
	IHouseService sevice;

	@Autowired
	ModelMapper modelMapper;

	@RequestMapping("/house")
	@ResponseBody
	public HouseDTO show(@PathVariable(value = "id") Long houseId) {
		House house = sevice.findById(houseId);
		HouseDTO dto = modelMapper.map(house, HouseDTO.class);
		return dto;
	}

}
