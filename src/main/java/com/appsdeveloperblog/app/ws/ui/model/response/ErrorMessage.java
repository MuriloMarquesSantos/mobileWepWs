package com.appsdeveloperblog.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {

    private Date timeStamp;
    private String message;
}
