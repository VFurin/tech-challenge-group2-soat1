package com.techchallenge.adapter.dto.pagamentos;

public class PagamentoPixResponseDTO {
    private Long id;
    private String status;
    private String detalhes;
    private String qrCodeBase64;
    private String qrCode;

    public PagamentoPixResponseDTO(Long id, String status, String detalhes, String qrCodeBase64, String qrCode) {
        this.id = id;
        this.status = status;
        this.detalhes = detalhes;
        this.qrCodeBase64 = qrCodeBase64;
        this.qrCode = qrCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getQrCodeBase64() {
        return qrCodeBase64;
    }

    public void setQrCodeBase64(String qrCodeBase64) {
        this.qrCodeBase64 = qrCodeBase64;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
