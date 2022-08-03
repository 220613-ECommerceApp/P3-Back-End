package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orderhistory_item")
public class OrderHistoryItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

	@ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	@ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

    @Column(name="quantity")
	private int quantity;

    @Column(name="purchase_time", insertable=false)
    private Timestamp purchaseTime;
}
