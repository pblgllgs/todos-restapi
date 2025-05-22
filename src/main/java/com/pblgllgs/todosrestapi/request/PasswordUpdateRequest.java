package com.pblgllgs.todosrestapi.request;
/*
 *
 * @author pblgl
 * Created on 16-05-2025
 *
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {

    @NotNull(message = "Old password is mandatory")
    @Size(min = 5, max = 30, message = "First name must be at lest 3 characters long")
    @Schema(example = "pass123")
    private String oldPassword;
    @NotNull(message = "New password is mandatory")
    @Size(min = 5, max = 30, message = "First name must be at lest 3 characters long")
    private String newPassword;
    @NotNull(message = "Confirmation password is mandatory")
    @Size(min = 5, max = 30, message = "First name must be at lest 3 characters long")
    private String newPassword2;

    public PasswordUpdateRequest(String oldPassword, String newPassword, String newPassword2) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPassword2 = newPassword2;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
