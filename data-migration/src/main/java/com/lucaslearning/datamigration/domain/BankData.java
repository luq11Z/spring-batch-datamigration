package com.lucaslearning.datamigration.domain;

import java.io.Serializable;

public class BankData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer personId;
	private Integer agency;
	private Integer account;
	private Integer bank;

	public BankData() {

	}

	public BankData(Integer id, Integer personId, Integer agency, Integer account, Integer bank) {
		this.id = id;
		this.personId = personId;
		this.agency = agency;
		this.account = account;
		this.bank = bank;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getAgency() {
		return agency;
	}

	public void setAgency(Integer agency) {
		this.agency = agency;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Integer getBank() {
		return bank;
	}

	public void setBank(Integer bank) {
		this.bank = bank;
	}

}
