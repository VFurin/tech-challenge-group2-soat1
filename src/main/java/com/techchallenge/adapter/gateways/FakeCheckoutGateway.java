package com.techchallenge.adapter.gateways;

import com.techchallenge.core.domain.entities.Pedido;

public interface FakeCheckoutGateway {

	Pedido checkout(Pedido pedido);
}
