package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private int productId;

	@ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
	private int userId;

    @Column(name="quantity")
	private int quantity;

    @Column(name="purchase_time", insertable=false)
    private Timestamp purchaseTime;
}
