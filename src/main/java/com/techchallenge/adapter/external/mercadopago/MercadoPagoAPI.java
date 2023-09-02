package com.techchallenge.adapter.external.mercadopago;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.techchallenge.adapter.driver.exceptionhandler.MercadoPagoException;
import com.techchallenge.adapter.dto.pagamentos.PagamentoPixDTO;
import com.techchallenge.adapter.dto.pagamentos.PagamentoPixResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoAPI {
    @Value("${mercadopago.access_token}")
    private String mercadoPagoAccessToken;

    @Value("${mercadopago.notification_url}")
    private String mercadoPagoNotificationUrl;

    public PagamentoPixResponseDTO efetuarPagamentoViaPix(PagamentoPixDTO pagamentoPixDTO) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(pagamentoPixDTO.getTotal())
                            .description(pagamentoPixDTO.getDescricao())
                            .paymentMethodId("pix")
                            .notificationUrl(mercadoPagoNotificationUrl)
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(pagamentoPixDTO.getCliente().getEmail())
                                            .firstName(pagamentoPixDTO.getCliente().getNome())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(pagamentoPixDTO.getCliente().getDocumento().getTipo())
                                                            .number(pagamentoPixDTO.getCliente().getDocumento().getNumero())
                                                            .build()
                                            )
                                            .build()
                            )
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            return new PagamentoPixResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCode()
            );
        } catch (MPApiException apiException) {
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            throw new MercadoPagoException(exception.getMessage());
        }
    }
}