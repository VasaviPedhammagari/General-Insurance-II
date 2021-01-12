package com.lti.dao;

import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao extends GenericDao{
	
	public boolean isVehiclePresent(String regNo) {

		return (Long) entityManager
				.createQuery("select count(v) from Vehicle v where v.regNo = :regNo")
		        .setParameter("regNo", regNo).getSingleResult() == 1 ? true : false;
}

}
