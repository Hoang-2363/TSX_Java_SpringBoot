package com.spring.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "san_pham")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ten_hang;
	private double gia_nhap;
	private double gia_ban;
	private String dvt;
	private int slt;
	private String loai_hang_hoa;

	@Column(columnDefinition = "TEXT")
	private String ghi_chu;
	private Date ngay_nhap;
	private String anh;

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen_hang() {
		return ten_hang;
	}

	public void setTen_hang(String ten_hang) {
		this.ten_hang = ten_hang;
	}

	public double getGia_nhap() {
		return gia_nhap;
	}

	public void setGia_nhap(double gia_nhap) {
		this.gia_nhap = gia_nhap;
	}

	public double getGia_ban() {
		return gia_ban;
	}

	public void setGia_ban(double gia_ban) {
		this.gia_ban = gia_ban;
	}

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvt) {
		this.dvt = dvt;
	}

	public int getSlt() {
		return slt;
	}

	public void setSlt(int slt) {
		this.slt = slt;
	}

	public String getLoai_hang_hoa() {
		return loai_hang_hoa;
	}

	public void setLoai_hang_hoa(String loai_hang_hoa) {
		this.loai_hang_hoa = loai_hang_hoa;
	}

	public String getGhi_chu() {
		return ghi_chu;
	}

	public void setGhi_chu(String ghi_chu) {
		this.ghi_chu = ghi_chu;
	}

	public Date getNgay_nhap() {
		return ngay_nhap;
	}

	public void setNgay_nhap(Date ngay_nhap) {
		this.ngay_nhap = ngay_nhap;
	}

}
