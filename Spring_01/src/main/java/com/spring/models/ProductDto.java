package com.spring.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDto {
	@NotEmpty(message = "Tên hàng hóa không được để trống")
	private String ten_hang;
	@NotEmpty(message = "Đơn vị tính không được để trống")
	private String dvt;
	@NotEmpty(message = "Loại hàng hóa không được để trống")
	private String loai_hang_hoa;
	@Min(0)
	private int slt;
	@Min(0)
	private double gia_nhap;
	@Min(0)
	private double gia_ban;

	@Size(min = 10, message = "Ghi chú ít nhất 10 ký tự")
	@Size(max = 2000, message = "Ghi chú nhiều nhất 2000 ký tự")
	private String ghi_chu;

	private MultipartFile anh;

	public String getTen_hang() {
		return ten_hang;
	}

	public void setTen_hang(String ten_hang) {
		this.ten_hang = ten_hang;
	}

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvt) {
		this.dvt = dvt;
	}

	public String getLoai_hang_hoa() {
		return loai_hang_hoa;
	}

	public void setLoai_hang_hoa(String loai_hang_hoa) {
		this.loai_hang_hoa = loai_hang_hoa;
	}

	public int getSlt() {
		return slt;
	}

	public void setSlt(int slt) {
		this.slt = slt;
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

	public String getGhi_chu() {
		return ghi_chu;
	}

	public void setGhi_chu(String ghi_chu) {
		this.ghi_chu = ghi_chu;
	}

	public MultipartFile getAnh() {
		return anh;
	}

	public void setAnh(MultipartFile anh) {
		this.anh = anh;
	}
}
