package com.iapurba.bookapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class BarcodeUtil {
    public static String generateBarcode(Long id, String isbn, LocalDate publicationDate, Long rackId) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        StringBuilder barcodeBuilder = new StringBuilder();

        barcodeBuilder.append("B").append(id)
                .append("-ISBN").append(isbn)
                .append("-P").append(publicationDate.format(dateTimeFormatter));

        // Add the rackId if it's available
        if (rackId != null) {
            barcodeBuilder.append("-R").append(rackId);
        }

        // Use a UUID to ensure uniqueness
        String uuid = UUID.randomUUID().toString().substring(1, 6);
        barcodeBuilder.append("-").append(uuid);

        return barcodeBuilder.toString();
    }
}
