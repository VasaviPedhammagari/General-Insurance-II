package com.lti.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.VehicleModels;

@Repository
public class VehicleDao extends GenericDao {

	public VehicleModels findPriceOfVehicle(String manufacturer, String model) {
		return (VehicleModels) entityManager
				.createQuery("select v from VehicleModels v where v.manufacturer = :p1 and v.model = :p2")
				.setParameter("p1", manufacturer).setParameter("p2", model).getSingleResult();
	}

	public boolean isVehiclePresent(String regNo) {

		return (Long) entityManager.createQuery("select count(v) from Vehicle v where v.regNo = :regNo")
				.setParameter("regNo", regNo).getSingleResult() == 1 ? true : false;
	}
	
	public boolean isVehicleModelPresent(String manufacturer,String model){
		
		return (Long) entityManager.createQuery("select count(v) from VehicleModels v where v.manufacturer = :manufacturer and v.model = :model")
				.setParameter("manufacturer", manufacturer).setParameter("model", model).getSingleResult() == 1 ? true : false; 
	}
	public boolean isVehicleIdPresent(int id) {
		return (Long) entityManager.createQuery("select count(v) from VehicleModels v where v.modelId = :id")
				.setParameter("id", id).getSingleResult() == 1 ? true : false;
	}
	public <A> List<A> fetchAllOrderById(Class<A> cname, int id) {
		String jpql = "select obj from " + cname.getName() + " as obj order by "+ id ;
		Query q = entityManager.createQuery(jpql);
		List<A> list = q.getResultList();

		return list;
	}

}
