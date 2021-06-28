package com.tw.banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

  @Test
  void should_return_negative_1_when_compare_to_given_yesterday_and_today_transactions() {
    // given
    Transaction yesterdayTransaction = new Transaction("27/06/2021", 100);
    Transaction todayTransaction = new Transaction("28/06/2021", 100);

    // when
    int actual = yesterdayTransaction.compareTo(todayTransaction);

    // then
    assertEquals(-1, actual);
  }

  @Test
  void should_return_positive_1_when_compare_to_given_yesterday_and_today_transactions() {
    // given
    Transaction yesterdayTransaction = new Transaction("27/06/2021", 100);
    Transaction todayTransaction = new Transaction("28/06/2021", 100);

    // when
    int actual = todayTransaction.compareTo(yesterdayTransaction);

    // then
    assertEquals(1, actual);
  }

  @Test
  void should_return_positive_1_when_compare_to_given_two_transactions_on_the_same_day() {
    // given
    Transaction todayTransaction1 = new Transaction("28/06/2021", 100);
    Transaction todayTransaction2 = new Transaction("28/06/2021", 100);

    // when
    int actual = todayTransaction1.compareTo(todayTransaction2);

    // then
    assertEquals(1, actual);
  }
}
