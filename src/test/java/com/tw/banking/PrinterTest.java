package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PrinterTest {

  @InjectMocks private Printer printer;
  @Mock private Console console;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void should_print_statement_when_print_given_a_transaction_list() {
    // given
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction("28/06/2021", 200));
    transactions.add(new Transaction("28/06/2021", -100));

    // when
    printer.print(transactions);

    // then
    assertAll(
            () -> verify(console, times(1)).printLine(Printer.STATEMENT_HEADER),
            () -> verify(console, times(1)).printLine("28/06/2021 | -100 | 100"),
            () -> verify(console, times(1)).printLine("28/06/2021 | 200 | 200")
    );
  }

  @Test
  void
      should_return_statement_line_when_statement_line_given_a_transaction_and_a_running_balance() {
    // given
    Transaction transaction = new Transaction("28/06/2021", 100);

    // when
    String actual = printer.statementLine(transaction, 200);

    // then
    assertEquals("28/06/2021 | 100 | 200", actual);
  }
}
