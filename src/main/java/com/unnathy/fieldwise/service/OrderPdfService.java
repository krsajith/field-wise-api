package com.unnathy.fieldwise.service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.district.District;
import com.unnathy.fieldwise.district.DistrictRepository;
import com.unnathy.fieldwise.order.Order;
import com.unnathy.fieldwise.order.OrderRepository;
import com.unnathy.fieldwise.orderitem.OrderItem;
import com.unnathy.fieldwise.orderitem.OrderItemRepository;
import com.unnathy.fieldwise.place.Place;
import com.unnathy.fieldwise.place.PlaceRepository;
import com.unnathy.fieldwise.product.Product;
import com.unnathy.fieldwise.product.ProductRepository;
import com.unnathy.fieldwise.shop.Shop;
import com.unnathy.fieldwise.shop.ShopRepository;
import com.unnathy.fieldwise.state.State;
import com.unnathy.fieldwise.state.StateRepository;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderPdfService {

    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final PlaceRepository placeRepository;
    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;

    public byte[] generateOrderPdf(Long orderId) throws UnnathyError {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Order not found", null));

        Shop shop = shopRepository.findById(order.getShopId())
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Shop not found", null));

        User user = userRepository.findById(order.getUserId())
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "User not found", null));

        String placeName = getEntityName(shop.getPlaceId(), placeRepository);
        String districtName = getEntityName(shop.getDistrictId(), districtRepository);
        String stateName = getEntityName(shop.getStateId(), stateRepository);

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        return createPdf(order, shop, user, placeName, districtName, stateName, orderItems);
    }

    private <T> String getEntityName(Long id, JpaRepository<T, Long> repository) {
        if (id == null) return "";
        return repository.findById(id)
                .map(entity -> {
                    try {
                        return (String) entity.getClass().getMethod("getName").invoke(entity);
                    } catch (Exception e) {
                        return "";
                    }
                })
                .orElse("");
    }

    private byte[] createPdf(Order order, Shop shop, User user, String placeName,
                             String districtName, String stateName, List<OrderItem> orderItems) throws UnnathyError {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold");
            PdfFont normalFont = PdfFontFactory.createFont("Helvetica");

            addHeader(document, boldFont);
            addOrderAndShopDetails(document, order, shop, user, placeName, districtName, stateName, normalFont, boldFont);
            addItemsTable(document, orderItems, normalFont, boldFont);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new UnnathyError("PDF_GENERATION_ERROR", "Failed to generate PDF: " + e.getMessage(), null);
        }
    }

    private void addHeader(Document document, PdfFont boldFont) {
        Paragraph header = new Paragraph("Tauruz Agencies")
                .setFont(boldFont)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(header);
    }

    private void addOrderAndShopDetails(Document document, Order order, Shop shop, User user,
                                        String placeName, String districtName, String stateName,
                                        PdfFont normalFont, PdfFont boldFont) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Table detailsTable = new Table(UnitValue.createPercentArray(new float[]{1, 1}))
                .useAllAvailableWidth()
                .setMarginBottom(20);

        Table leftColumn = new Table(1).useAllAvailableWidth();
        leftColumn.addCell(createDetailCell("Order Details", boldFont, true));
        leftColumn.addCell(createDetailCell("Order Code: " + (order.getOrderNumber() != null ? order.getOrderNumber() : "N/A"), normalFont, false));
        leftColumn.addCell(createDetailCell("Customer: " + user.getFirstName() + " " + (user.getLastName() != null ? user.getLastName() : ""), normalFont, false));
        leftColumn.addCell(createDetailCell("Delivery Date: " + (order.getDeliveryDate() != null ? order.getDeliveryDate().format(dateFormatter) : "N/A"), normalFont, false));
        leftColumn.addCell(createDetailCell("Delivery Mode: " + (order.getDeliveryMode() != null ? order.getDeliveryMode() : "N/A"), normalFont, false));
        leftColumn.addCell(createDetailCell("Order Date: " + (order.getOrderDate() != null ? order.getOrderDate().format(dateFormatter) : "N/A"), normalFont, false));

        Table rightColumn = new Table(1).useAllAvailableWidth();
        rightColumn.addCell(createDetailCell("Shop Details", boldFont, true));
        rightColumn.addCell(createDetailCell("Shop Name: " + shop.getName(), normalFont, false));
        rightColumn.addCell(createDetailCell("Location: " + placeName, normalFont, false));
        rightColumn.addCell(createDetailCell("District: " + districtName, normalFont, false));
        rightColumn.addCell(createDetailCell("State: " + stateName, normalFont, false));

        detailsTable.addCell(new Cell().add(leftColumn).setBorder(null).setPadding(5));
        detailsTable.addCell(new Cell().add(rightColumn).setBorder(null).setPadding(5));

        document.add(detailsTable);
    }

    private Cell createDetailCell(String text, PdfFont font, boolean isHeader) {
        Cell cell = new Cell()
                .add(new Paragraph(text).setFont(font).setFontSize(isHeader ? 12 : 10))
                .setBorder(null)
                .setPadding(2);
        if (isHeader) {
            cell.setBackgroundColor(new DeviceRgb(230, 230, 230));
        }
        return cell;
    }

    private void addItemsTable(Document document, List<OrderItem> orderItems, PdfFont normalFont, PdfFont boldFont) {
        Table itemsTable = new Table(UnitValue.createPercentArray(new float[]{1, 3, 2, 1.5f, 1.5f, 2}))
                .useAllAvailableWidth()
                .setMarginBottom(20);

        String[] headers = {"Sl No", "Product Name", "Product Code", "Qty", "Rate", "Total"};
        for (String header : headers) {
            itemsTable.addHeaderCell(new Cell()
                    .add(new Paragraph(header).setFont(boldFont).setFontSize(10))
                    .setBackgroundColor(new DeviceRgb(200, 200, 200))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setPadding(5)
                    .setBorder(new SolidBorder(ColorConstants.BLACK, 1)));
        }

        BigDecimal totalQuantity = BigDecimal.ZERO;
        BigDecimal grandTotal = BigDecimal.ZERO;
        int slNo = 1;

        for (OrderItem item : orderItems) {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            String productName = product != null ? product.getName() : "Unknown";
            String productCode = product != null ? product.getCode() : "N/A";

            itemsTable.addCell(createTableCell(String.valueOf(slNo++), normalFont, TextAlignment.CENTER));
            itemsTable.addCell(createTableCell(productName, normalFont, TextAlignment.LEFT));
            itemsTable.addCell(createTableCell(productCode, normalFont, TextAlignment.CENTER));
            itemsTable.addCell(createTableCell(formatDecimal(item.getQuantity()), normalFont, TextAlignment.RIGHT));
            itemsTable.addCell(createTableCell(formatDecimal(item.getUnitPrice()), normalFont, TextAlignment.RIGHT));
            itemsTable.addCell(createTableCell(formatDecimal(item.getLineTotal()), normalFont, TextAlignment.RIGHT));

            totalQuantity = totalQuantity.add(item.getQuantity() != null ? item.getQuantity() : BigDecimal.ZERO);
            grandTotal = grandTotal.add(item.getLineTotal() != null ? item.getLineTotal() : BigDecimal.ZERO);
        }

        itemsTable.addCell(new Cell(1, 3)
                .add(new Paragraph("Total").setFont(boldFont).setFontSize(10))
                .setTextAlignment(TextAlignment.RIGHT)
                .setPadding(5)
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1))
                .setBackgroundColor(new DeviceRgb(240, 240, 240)));

        itemsTable.addCell(new Cell()
                .add(new Paragraph(formatDecimal(totalQuantity)).setFont(boldFont).setFontSize(10))
                .setTextAlignment(TextAlignment.RIGHT)
                .setPadding(5)
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1))
                .setBackgroundColor(new DeviceRgb(240, 240, 240)));

        itemsTable.addCell(new Cell()
                .add(new Paragraph("").setFont(boldFont).setFontSize(10))
                .setPadding(5)
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1))
                .setBackgroundColor(new DeviceRgb(240, 240, 240)));

        itemsTable.addCell(new Cell()
                .add(new Paragraph(formatDecimal(grandTotal)).setFont(boldFont).setFontSize(10))
                .setTextAlignment(TextAlignment.RIGHT)
                .setPadding(5)
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1))
                .setBackgroundColor(new DeviceRgb(240, 240, 240)));

        document.add(itemsTable);
    }

    private Cell createTableCell(String text, PdfFont font, TextAlignment alignment) {
        return new Cell()
                .add(new Paragraph(text).setFont(font).setFontSize(9))
                .setTextAlignment(alignment)
                .setPadding(5)
                .setBorder(new SolidBorder(ColorConstants.BLACK, 1));
    }

    private String formatDecimal(BigDecimal value) {
        if (value == null) return "0.00";
        return String.format("%.2f", value);
    }
}
