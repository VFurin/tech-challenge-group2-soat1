package com.techchallenge.adapter.dto.pagamentos;

public class PagamentoPixResponseDTO extends PagamentoResponseDTO {
    private String qrCodeBase64;
    private String qrCode;

    public PagamentoPixResponseDTO(Long id, String status, String detalhes, String tipo, String qrCodeBase64, String qrCode) {
    	super(id, status, detalhes, tipo);
    	
        this.qrCodeBase64 = qrCodeBase64;
        this.qrCode = qrCode;
    }

    public String getQrCodeBase64() {
        return qrCodeBase64;
    }

    public String getQrCode() {
        return qrCode;
    }
}
