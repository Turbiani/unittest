package br.com.leonardo.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MetricsTest {
	Metrics metrics = new Metrics();

	@Test
	public void when_NULL_RETURNS_zero() {
		assertEquals(0, metrics.absSum(null, null));
	}

	@Test
	public void when_second_is_null_return_abs_first() {
		assertEquals(2, metrics.absSum(2, null));
	}

	@Test
	public void when_first_is_null_return_abs_second() {
		assertEquals(1, metrics.absSum(null, 1));
	}

	@Test
	public void when_sum_abs_when_first_and_second_is_not_null() {
		assertEquals(4, metrics.absSum(2, 2));
	}
}