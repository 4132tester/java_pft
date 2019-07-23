package first.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimes() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 1));
  }

  @Test (enabled = false)
  public void testNoRun() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 1));
  }

}
