package com.bottega.withdraw.domain;

import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.withdraw.domain.produces.MoneyWasWithdrawn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreditCard {

	private List<DomainEvent> events = new ArrayList<>();

	private UUID cardId;
	private BigDecimal limit;
	private BigDecimal debit = BigDecimal.ZERO;


	CreditCard(BigDecimal limit, UUID cardId) {
		this.limit = limit;
		this.cardId = cardId;
	}

	void withdraw(BigDecimal amount) {


		if(limit.compareTo(amount.add(debit)) <= 0) {
			throw new RuntimeException();
		}
		debit = debit.add(amount);

		events.add(new MoneyWasWithdrawn(cardId,amount));
	}

	public List<DomainEvent> getEvents() {
		return events;
	}

	public void refund(BigDecimal amount) {
	}
}
