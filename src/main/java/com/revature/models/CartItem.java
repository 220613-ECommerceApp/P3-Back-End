package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private int quantity;
	@ManyToOne(targetEntity = Product.class)
	private int productId;
	@ManyToOne(targetEntity = User.class)
	private int userId;
}
