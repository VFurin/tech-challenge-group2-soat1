package com.techchallenge.adapter.driver.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
public final class Problem {
	
	public static final class ProblemBuilder {
		
		private Integer status;
		private String type;
		private String title;
		private String detail;
		private String userMessage;
		private OffsetDateTime timestamp;
		private List<Object> objects;
		

		private ProblemBuilder() {
			
		}
		
		public final ProblemBuilder status(Integer status) {
			this.status = status;
			return this;
		}
		
		public final ProblemBuilder type(String type) {
			this.type = type;
			return this;
		}
		
		public final ProblemBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public final ProblemBuilder detail(String detail) {
			this.detail = detail;
			return this;
		}
		
		public ProblemBuilder timestamp(OffsetDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		public ProblemBuilder userMessage(String userMessage) {
			this.userMessage = userMessage;
			return this;
		}
		
		public ProblemBuilder objects(List<Object> objects) {
			this.objects = objects;
			return this;
		}
		
		public final Problem build() {
			return new Problem(this.status, this.type, this.title, this.detail, this.userMessage, this.timestamp, this.objects);
		}
	}
	
	public static final class ObjectBuilder {
		
		private String name;
		private String userMessage;
		
		private ObjectBuilder() {
			
		}
		
		public ObjectBuilder userMessage(String userMessage) {
			this.userMessage = userMessage;
			return this;
		}
		
		public ObjectBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public final Problem.Object build() {
			return new Problem.Object(this.name, this.userMessage);
		}
		
	}

	@ApiModel("ObjetoProblema")
	public static class Object {
		
		public Object(String name, String userMessage) {
			this.name = name;
			this.userMessage = userMessage;
		}
		
		@ApiModelProperty(example = "preco")
		private String name;
		@ApiModelProperty(example = "Preço é obrigatório.")
		private String userMessage;
		
		public static final ObjectBuilder builder() {
			return new ObjectBuilder();
		}
		
		public String getName() {
			return name;
		}
		
		public String getUserMessage() {
			return userMessage;
		}
	}
	
	private Problem(Integer status, String type, String title, String detail, String userMessage, OffsetDateTime timestamp, List<Object> objects) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
		this.userMessage = userMessage;
		this.timestamp = timestamp;
		this.objects = objects;
	}
	
	public static final ProblemBuilder builder() {
		return new ProblemBuilder();
	}
	
	@ApiModelProperty(example = "400")
	private Integer status;
	@ApiModelProperty(example = "http://localhost:8080/dados-invalidos")
	private String type;
	@ApiModelProperty(example = "Dados inválidos")
	private String title;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento corretamente.")
	private String detail;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento corretamente.")
	private String userMessage;
	@ApiModelProperty(example = "2019-12-01T18:10:23.70844Z")
	private OffsetDateTime timestamp;
	@ApiModelProperty(value = "Objetos ou campos que geraram o erro.")
	private List<Object> objects;

	public Integer getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public List<Object> getObjects() {
		return objects;
	}
}