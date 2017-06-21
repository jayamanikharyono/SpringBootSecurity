package com.itdel.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.itdel.model.Laporan;
import com.itdel.model.Role;

@Service("laporanService")
public interface LaporanService {
	public Laporan getLaporan(int id_Laporan);
	public List<Laporan> getAllLaporan();
	public List<Laporan> getAllLaporanByRole(Set<Role> roles);
	public void saveLaporan(Laporan Laporan);
	public void deletelaporan(Laporan laporan);
}
