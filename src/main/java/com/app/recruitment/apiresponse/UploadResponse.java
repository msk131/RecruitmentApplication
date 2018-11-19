package com.app.recruitment.apiresponse;

 import lombok.Builder;
import lombok.Value;

@Value
@Builder
 public class UploadResponse {
     private final String message;
     private final String title;
     private final String details;
     private final String fileName;
}
