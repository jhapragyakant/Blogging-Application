package com.pragyakantjha.blogging.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    Integer  fieldValue;
    String fieldResource;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
        super(String.format("%s not found with %s : %d", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldResource) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldResource));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldResource = fieldResource;
    }


}
