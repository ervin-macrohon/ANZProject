package com.fdmgroup.application.factories;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.application.entities.Transaction;
import com.fdmgroup.application.tables.TransactionsTableRow;

public class TransactionsTableFactoryTest {
	@InjectMocks
	private TransactionsTableFactory tableFactory;
	@Mock
	private TransactionsTableRowFactory rowFactory;
	private List<Transaction> list;
	@Mock
	private Transaction trans1;
	@Mock
	private Transaction trans2;
	@Mock
	private Transaction trans3;
	@Mock
	private TransactionsTableRow row1;
	@Mock
	private TransactionsTableRow row2;
	@Mock
	private TransactionsTableRow row3;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		list = new ArrayList<>();
		list.add(trans1);
		list.add(trans2);
		list.add(trans3);
	}
	
	@Test
	public void creates_table_row_for_each_element_in_the_list() {
		when(rowFactory.createTableRow(trans1)).thenReturn(row1);
		when(rowFactory.createTableRow(trans2)).thenReturn(row2);
		when(rowFactory.createTableRow(trans3)).thenReturn(row3);
		
		List<TransactionsTableRow> table = tableFactory.createTable(list);

		verify(rowFactory).createTableRow(trans1);
		verify(rowFactory).createTableRow(trans2);
		verify(rowFactory).createTableRow(trans3);
		assertEquals(3, table.size());
		assertEquals(row1, table.get(0));
		assertEquals(row2, table.get(1));
		assertEquals(row3, table.get(2));
	}
}
