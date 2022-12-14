package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.DetalleVenta;

@Repository
@Transactional
public class DetalleVentaRepositoryImpl implements IDetalleVentaRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(DetalleVenta detalle) {
		// TODO Auto-generated method stub
		this.entityManager.persist(detalle);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<DetalleVenta> buscar(LocalDateTime fecha,String categoria, Integer cantidad) {
		TypedQuery<DetalleVenta> myQuery = this.entityManager.createQuery(
				"SELECT d FROM DetalleVenta d JOIN FETCH d.venta v, d.producto p WHERE v.fecha = :fecha AND p.categoria = :categoria AND d.cantidad >= cantidad",
				DetalleVenta.class);
		myQuery.setParameter("fecha", fecha);
		myQuery.setParameter("categoria", categoria);
		myQuery.setParameter("cantidad", cantidad);
		
		return myQuery.getResultList();
		
	}
	
}
