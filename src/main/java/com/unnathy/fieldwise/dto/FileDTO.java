package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO extends BaseDTO {

    private Long uploadedById;
    private String fileName;
    private Long fileSize;
    private String fileType;
    private String storagePath;
    private String entityType;
    private Long entityId;
}
