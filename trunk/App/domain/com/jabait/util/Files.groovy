package com.jabait.util

import com.jabait.security.User

abstract class Files {
    static mapping = {
        table("files")
        discriminator(column: [name: "file_type", length: 255], value: "file")
    }

    String prefix;
    String generatedName;
    String postfix;
    String extension;
    String mimeType;
    Date addedDate;
    User uploadedBy;

    static constraints = {
    }
}
