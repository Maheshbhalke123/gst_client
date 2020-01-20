package com.example.model;

public class Gst {
	private int id;
	private double cgstRate;
	private double sgstRate;

	public Gst() {

	}

	public Gst(double cgstRate, double sgstRate) {
		super();
		this.cgstRate = cgstRate;
		this.sgstRate = sgstRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(double cgstRate) {
		this.cgstRate = cgstRate;
	}

	public double getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(double sgstRate) {
		this.sgstRate = sgstRate;
	}

	@Override
	public String toString() {
		return "Gst [id=" + id + ", cgstRate=" + cgstRate + ", sgstRate=" + sgstRate + "]";
	}

}
