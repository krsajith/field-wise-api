package com.unnathy.fieldwise.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
