# DTO Generation Utility

This script automatically generates Data Transfer Object (DTO) classes for all your entities.

## What are DTOs?

DTOs (Data Transfer Objects) are simple POJOs used to transfer data between layers:
- **Decouple** API contracts from database entities
- **Prevent** LazyInitializationException with JPA relationships
- **Control** exactly what data is exposed in your API
- **Simplify** JSON serialization/deserialization

## What the Script Does

The `generate_dtos.py` script:
1. Creates a `BaseDTO` class with common fields (`id`, `createdAt`, `updatedAt`)
2. Generates a DTO for each entity with:
   - All simple fields (String, Long, BigDecimal, etc.)
   - Relationships converted to ID fields (e.g., `Shop shop` → `Long shopId`)
   - Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`)
3. Skips collections (`@OneToMany`, `@ManyToMany`) by default
4. Extends `BaseDTO` for consistency

## Example Transformation

### Entity (Order.java)
```java
@Entity
public class Order extends BaseEntity {
    private String orderNumber;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private User user;

    private BigDecimal totalAmount;
    private String status;
}
```

### Generated DTO (OrderDTO.java)
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends BaseDTO {
    private String orderNumber;
    private Long shopId;
    private Long userId;
    private BigDecimal totalAmount;
    private String status;
}
```

## Usage

### Prerequisites
- Python 3.6 or higher

### Running the Script

```bash
# From utils directory
cd utils
python generate_dtos.py
```

Or from project root:
```bash
python utils/generate_dtos.py
```

### Output

```
======================================================================
DTO Generation Script
======================================================================

Entity directory: ...\entity
DTO directory:    ...\dto

✓ Created DTO directory

✓ Created BaseDTO.java

Found 45 entity files

----------------------------------------------------------------------
✓ Activity                     Created ActivityDTO.java
✓ Attendance                   Created AttendanceDTO.java
✓ Order                        Created OrderDTO.java
...
⊘ BaseEntity                   SKIPPED
----------------------------------------------------------------------

Summary:
  Total entities found:  45
  Processed:             42
  DTOs created:          43
  Skipped:               3
  Errors:                0

======================================================================

✓ DTO generation completed successfully!
```

## Generated Structure

```
src/main/java/com/unnathy/fieldwise/
├── entity/
│   ├── BaseEntity.java
│   ├── Order.java
│   ├── Product.java
│   └── ...
└── dto/
    ├── BaseDTO.java          (generated)
    ├── OrderDTO.java         (generated)
    ├── ProductDTO.java       (generated)
    └── ...
```

## Customization After Generation

### 1. Add Nested DTOs for Important Relationships

Instead of just IDs, you might want nested objects:

```java
@Data
public class OrderDTO extends BaseDTO {
    private String orderNumber;
    private Long shopId;
    private String shopName;        // Add this manually
    private Long userId;
    private String userName;         // Add this manually
    private BigDecimal totalAmount;
    private String status;
}
```

### 2. Add Response-Specific Fields

```java
@Data
public class OrderDTO extends BaseDTO {
    // ... existing fields ...

    private List<OrderItemDTO> items;  // Add collection manually
    private String formattedTotal;     // Add computed field
}
```

### 3. Create Request/Response Variants

You might want separate DTOs for requests vs responses:

```java
// For API responses
public class OrderResponseDTO extends BaseDTO { ... }

// For create requests (no ID)
@Data
public class CreateOrderRequest {
    private Long shopId;
    private List<OrderItemRequest> items;
}

// For update requests
@Data
public class UpdateOrderRequest {
    private String status;
    private String remarks;
}
```

## Creating Mappers

### Option 1: MapStruct (Recommended)

Add MapStruct dependency to `pom.xml`:
```xml
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.5.Final</version>
</dependency>
```

Create mapper interface:
```java
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO dto);
    List<OrderDTO> toDTOList(List<Order> orders);
}
```

### Option 2: Manual Mapping

```java
@Service
public class OrderMapper {
    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setShopId(order.getShop() != null ? order.getShop().getId() : null);
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        return dto;
    }
}
```

## Using DTOs in Controllers

### Before (using entities directly - NOT RECOMMENDED)
```java
@GetMapping("/{id}")
public Order getOrder(@PathVariable Long id) {
    return orderService.findById(id);  // Exposes entity directly
}
```

### After (using DTOs - RECOMMENDED)
```java
@GetMapping("/{id}")
public OrderDTO getOrder(@PathVariable Long id) {
    Order order = orderService.findById(id);
    return orderMapper.toDTO(order);  // Returns DTO
}
```

## Configuration

Edit the script to customize behavior:

```python
# At the top of generate_dtos.py

# Skip specific entities
SKIP_ENTITIES = ["BaseEntity", "VActiveUser", "VPendingApproval", "VTodayAttendance"]

# Change output directory
DTO_DIR = Path(r"D:\workspace\field-wise\FieldWise\src\main\java\com\unnathy\fieldwise\dto")
```

## Advanced: Include Nested DTOs

Modify the script to include nested DTOs instead of just IDs:

In `generate_dto_content()` function, change:
```python
if field.is_relationship:
    # Convert relationship to nested DTO
    dto_type = f"{field.field_type}DTO"
    field_declarations.append(f"    private {dto_type} {field.name};")
```

## Troubleshooting

### Import errors after generation
- Ensure Lombok is in your dependencies
- Run `mvn clean compile` to generate Lombok code

### Circular dependency in nested DTOs
- Use IDs instead of nested DTOs for some relationships
- Or create separate simplified DTOs for nesting

### Collections not included
- The script skips `@OneToMany` and `@ManyToMany` by default
- Add them manually where needed to avoid circular references

## Best Practices

1. **Don't modify generated DTOs extensively** - Regenerate when entities change
2. **Create custom DTOs for complex use cases** - Don't force generated DTOs to fit
3. **Use different DTOs for different operations** - CreateRequest, UpdateRequest, Response
4. **Keep DTOs flat when possible** - Avoid deep nesting
5. **Document custom fields** - Add comments for manually added fields

## Next Steps

1. ✓ Generate DTOs with this script
2. Review and customize generated DTOs
3. Add MapStruct or create manual mappers
4. Update controllers to use DTOs
5. Update services to work with DTOs
6. Add validation annotations (`@NotNull`, `@Size`, etc.) to request DTOs
