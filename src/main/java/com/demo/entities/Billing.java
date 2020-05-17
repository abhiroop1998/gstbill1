package com.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Billing {
	//private Long id;
	@Id
	private Long bcode;
	private String bname;
	private int bprice;
	private float bgst;	
	private int bquantity;

	protected Billing() {
	}

	protected Billing(Long bcode,String bname, float bgst, int bprice,int bquantity) {
		super();
		this.bname = bname;
		this.bcode = bcode;
		this.bgst = bgst;
		this.bprice = bprice;
		this.bquantity=bquantity;
	}

	public Long getBcode() {
		return bcode;
	}

	public void setBcode(Long bcode) {
		this.bcode = bcode;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public float getBgst() {
		return bgst;
	}

	public void setBgst(float bgst) {
		this.bgst = bgst;
	}

	public int getBprice() {
		return bprice;
	}

	public void setBprice(int bprice) {
		this.bprice = bprice;
	}

	public int getBquantity() {
		return bquantity;
	}

	public void setBquantity(int bquantity) {
		this.bquantity = bquantity;
	}

	
}
