package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountTest {

  @InjectMocks private Account account;

  @Mock private Printer printer;
  @Mock private TransactionRepository transactionRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void should_add_deposit_when_deposit_given_an_amount() {
    // given
    int amount = 100;

    // when
    account.deposit(amount);

    // then
    verify(transactionRepository, times(1)).addDeposit(amount);
  }

  @Test
  void should_add_withdraw_when_withdraw_given_an_amount() {
    // given
    int amount = 100;

    // when
    account.withdraw(amount);

    // then
    verify(transactionRepository, times(1)).addWithdraw(amount);
  }

  @Test
  void should_print_when_print_statement_given_account() {
    // given
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction("28/06/2021", 100));
    when(transactionRepository.allTransactions()).thenReturn(transactions);

    // when
    account.printStatement();

    // then
    assertAll(
        () -> verify(transactionRepository, times(1)).allTransactions(),
        () -> verify(printer, times(1)).print(transactions));
  }
}
