package com.tao.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tao.model.Admin;

public class TestAdmin {

	@Test
	public void test() {
		Admin ad = new Admin();
		System.out.println("username:" + ad.getUsername());
		System.out.println("password:" + ad.getPassword());
		System.out.println("role:" + ad.getRole());
		System.out.println("id:" + ad.getId());
	}

}
