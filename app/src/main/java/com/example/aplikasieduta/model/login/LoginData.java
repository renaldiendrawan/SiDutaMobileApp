package com.example.aplikasieduta.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("nama_ibu")
	private String namaIbu;

	public void setNamaIbu(String namaIbu){
		this.namaIbu = namaIbu;
	}

	public String getNamaIbu(){
		return namaIbu;
	}
}