package com.lprates.bean;

public class DummyBean {

	private Integer id;

	private Long value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DummyBean [id=" + id + ", value=" + value + "]";
	}

}