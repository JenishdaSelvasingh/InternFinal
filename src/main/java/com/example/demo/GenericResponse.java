package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class GenericResponse {
    private String message;
    private Boolean status;

    protected GenericResponse(final GenericResponseBuilder<?, ?> b) {
        this.message = b.message;
        this.status = b.status;
    }

    public static GenericResponseBuilder<?, ?> builder() {
        return new GenericResponseBuilderImpl();
    }

    public String getMessage() {
        return this.message;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setStatus(final Boolean status) {
        this.status = status;
    }

    public String toString() {
        String var10000 = this.getMessage();
        return "GenericResponse(message=" + var10000 + ", status=" + this.getStatus() + ")";
    }

    public GenericResponse(final String message, final Boolean status) {
        this.message = message;
        this.status = status;
    }

    public GenericResponse() {
    }

    public abstract static class GenericResponseBuilder<C extends GenericResponse, B extends GenericResponseBuilder<C, B>> {
        private String message;
        private Boolean status;

        public GenericResponseBuilder() {
        }

        public B message(final String message) {
            this.message = message;
            return this.self();
        }

        public B status(final Boolean status) {
            this.status = status;
            return this.self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "GenericResponse.GenericResponseBuilder(message=" + this.message + ", status=" + this.status + ")";
        }
    }

    private static final class GenericResponseBuilderImpl extends GenericResponseBuilder<GenericResponse, GenericResponseBuilderImpl> {
        private GenericResponseBuilderImpl() {
        }

        protected GenericResponseBuilderImpl self() {
            return this;
        }

        public GenericResponse build() {
            return new GenericResponse(this);
        }
    }
}
