package com.itdel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "laporan")
public class Laporan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "laporan_id")
	private int id;
	@Column(name = "nama_laporan")
	private String nama;
	@Column(name = "file_laporan")
	private String fileLaporan;
	@Column(name = "tanggalLaporan")
	private Date tanggalLaporan;
	@Column(name = "imageList")
	private String imageList;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "laporan_user", joinColumns = @JoinColumn(name = "laporan_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
	@Column(name = "roles")
	private String roles;
	
	public Laporan()
	{
		
	}
	
	public Laporan(int id, String nama, String fileLaporan, Date tanggalLaporan, String imageList, User user,
			String roles) {
		super();
		this.id = id;
		this.nama = nama;
		this.fileLaporan = fileLaporan;
		this.tanggalLaporan = tanggalLaporan;
		this.imageList = imageList;
		this.user = user;
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getFileLaporan() {
		return fileLaporan;
	}
	public void setFileLaporan(String fileLaporan) {
		this.fileLaporan = fileLaporan;
	}
	public Date getTanggalLaporan() {
		return tanggalLaporan;
	}
	public void setTanggalLaporan(Date tanggalLaporan) {
		this.tanggalLaporan = tanggalLaporan;
	}
	public String getImageList() {
		return imageList;
	}
	public void setImageList(String imageList) {
		this.imageList = imageList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString()
	{
		return "-----------------------" + id + nama + fileLaporan +tanggalLaporan+imageList+user.getName()+roles;
	}
}
