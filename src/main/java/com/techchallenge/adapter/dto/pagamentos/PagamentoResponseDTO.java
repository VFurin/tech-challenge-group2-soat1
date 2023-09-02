package com.techchallenge.adapter.dto.pagamentos;

public class PagamentoResponseDTO {

    private Long id;
    private String status;
    private String detalhes;
    private String tipo;
    
    public PagamentoResponseDTO(Long id, String status, String detalhes, String tipo) {
        this.id = id;
        this.status = status;
        this.detalhes = detalhes;
        this.tipo = tipo;
    }

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public String getTipo() {
		return tipo;
	}
    
}
