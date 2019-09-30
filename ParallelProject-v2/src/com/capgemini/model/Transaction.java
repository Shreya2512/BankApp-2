
//Model class to print transaction details

package com.capgemini.model;

public class Transaction {
private String type;
private double amt;
private double net_bal;


public Transaction(String type, double amt, double net_bal) {
	super();
	this.type = type;
	this.amt = amt;
	this.net_bal = net_bal;
}

public double getNet_bal() {
	return net_bal;
}

public void setNet_bal(double net_bal) {
	this.net_bal = net_bal;
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public double getAmt() {
	return amt;
}
public void setAmt(double amt) {
	this.amt = amt;
}
@Override
public String toString() {
	return "Transaction [Type =" + type + ", Amount=" + amt + ", Net Balance=" + net_bal + "]";
}

}
