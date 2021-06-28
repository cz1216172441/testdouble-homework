package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TransactionRepositoryTest {

  @InjectMocks private TransactionRepository transactionRepository;
  @Mock private Clock clock;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void should_add_a_positive_transaction_when_add_deposit_given_a_day_string_and_an_amount() {
    // given
    String today = "28/06/2021";
    when(clock.todayAsString()).thenReturn(today);

    // when
    transactionRepository.addDeposit(100);

    // then
    assertAll(
        () -> assertEquals(1, transactionRepository.transactions.size()),
        () -> assertEquals(today, transactionRepository.transactions.get(0).date()),
        () -> assertEquals(100, transactionRepository.transactions.get(0).amount()));
  }

  @Test
  void should_add_a_negative_transaction_when_add_withdraw_given_a_day_string_and_an_amount() {
    // given
    String today = "28/06/2021";
    when(clock.todayAsString()).thenReturn(today);

    // when
    transactionRepository.addWithdraw(100);

    // then
    assertAll(
            () -> assertEquals(1, transactionRepository.transactions.size()),
            () -> assertEquals(today, transactionRepository.transactions.get(0).date()),
            () -> assertEquals(-100, transactionRepository.transactions.get(0).amount()));
  }
}
