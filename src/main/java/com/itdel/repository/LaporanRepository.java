package com.itdel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itdel.model.Laporan;

@Repository("laporanRepository")
public interface LaporanRepository extends JpaRepository<Laporan, Integer>{
	@Query("SELECT p FROM Laporan p WHERE p.roles = :roles")
	public List<Laporan> getLaporanByRole(@Param("roles")String role);
}