package com.lprates.bean;

/**
 */
public class DummyBean {

	private Integer id;

	private Long value;

	/**
	 * Method getId.
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Method setId.
	 * @param id Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Method getValue.
	 * @return Long
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * Method setValue.
	 * @param value Long
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString() {
		return "DummyBean [id=" + id + ", value=" + value + "]";
	}

}