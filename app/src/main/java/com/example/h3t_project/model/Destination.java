package com.example.h3t_project.model;

public class Destination {
  private int id;
  private String address;
  private String province;
  private String district;
  private String ward;
  public Destination(int id, String address, String province, String district, String ward) {
    this.id = id;
    this.address = address;
    this.province = province;
    this.district = district;
    this.ward = ward;
  }
  public Destination(String address, String province, String district, String ward) {
    this.address = address;
    this.province = province;
    this.district = district;
    this.ward = ward;
  }

  public Destination() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getWard() {
    return ward;
  }

  public void setWard(String ward) {
    this.ward = ward;
  }

  @Override
  public String toString() {
    return address + "-" + ward + "-" + district + "-" + province;
  }
}
