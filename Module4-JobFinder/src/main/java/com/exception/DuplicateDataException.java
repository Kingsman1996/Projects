package com.exception;

import com.dto.EditUserInfoForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateDataException extends RuntimeException {
    private EditUserInfoForm editUserInfoForm;
    public DuplicateDataException(String message, EditUserInfoForm editUserInfoForm) {
        super(message);
        this.editUserInfoForm = editUserInfoForm;
    }
}
