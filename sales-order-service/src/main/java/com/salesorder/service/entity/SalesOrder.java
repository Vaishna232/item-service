package com.salesorder.service.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sales_order")
public class SalesOrder {
	
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private int id;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "cust_id")
	private int custId;
	
	@Column(name = "order_desc")
	private String orderDesc;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@OneToMany(targetEntity = OrderLineItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id",referencedColumnName="order_id")
	private List<OrderLineItem> orderLineItem;
	
	protected SalesOrder() {		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderLineItem> getOrderLineItem() {
		return orderLineItem;
	}

	public void setOrderLineItem(List<OrderLineItem> orderLineItem) {
		this.orderLineItem = orderLineItem;
	}

	
}

