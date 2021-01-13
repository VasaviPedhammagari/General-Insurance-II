package com.lti.dao;

import org.springframework.stereotype.Repository;

import com.lti.entity.VehicleModels;

@Repository
public class VehicleDao extends GenericDao{
	
	public VehicleModels findPriceOfVehicle(String manufacturer, String model) {
		return (VehicleModels) entityManager
				.createQuery("select v from VehicleModels v where v.manufacturer = :p1 and v.model = :p2")
				.setParameter("p1", manufacturer)
				.setParameter("p2", model)
				.getSingleResult();
	}
	
}
