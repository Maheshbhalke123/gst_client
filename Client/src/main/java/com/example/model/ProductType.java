package com.example.model;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Gst;;

public class ProductType {
	private Integer id;
	private String type;
	private List<Products> products = new ArrayList<>();
	private Gst gst;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Gst getGst() {
		return gst;
	}

	public void setGst(Gst gst) {
		this.gst = gst;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductType [id=" + id + ", type=" + type + ", products=" + products + ", gst=" + gst + "]";
	}

}
