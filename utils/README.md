# Code Generation Utilities

This directory contains Python utilities for automating common tasks in the FieldWise project.

## Available Scripts

1. **refactor_entities.py** - Refactor entities to extend BaseEntity
2. **generate_dtos.py** - Generate DTO classes for all entities

---

## 1. Entity Refactoring (refactor_entities.py)

Refactors entity classes to use a common `BaseEntity` base class.

## What the Script Does

The `refactor_entities.py` script automates the process of:
1. Adding `extends BaseEntity` to all entity classes
2. Removing duplicate fields (`id`, `createdAt`, `updatedAt`) from entity classes
3. Preserving all other entity-specific code and annotations

## BaseEntity Class

The `BaseEntity` abstract class contains common fields:
- `id` (Long) - Primary key with auto-generation
- `createdAt` (Instant) - Creation timestamp
- `updatedAt` (Instant) - Last update timestamp

## Usage

### Prerequisites
- Python 3.6 or higher
- Git (recommended for version control before running)

### Before Running
**IMPORTANT:** Commit your current changes to git first!
```bash
git add .
git commit -m "Before entity refactoring"
```

### Running the Script

```bash
# From the utils directory
cd utils
python refactor_entities.py
```

Or from the project root:
```bash
python utils/refactor_entities.py
```

### After Running

1. **Review the changes** in your IDE
2. **Build the project** to check for compilation errors
   ```bash
   mvn clean compile
   # or
   ./gradlew build
   ```
3. **Run tests** to ensure functionality is preserved
4. **Commit the changes** if everything looks good

## Excluded Entities

The script automatically skips:
- `BaseEntity` (the base class itself)
- `VActiveUser` (database view without proper @Id)
- `VPendingApproval` (database view without @Id)
- `VTodayAttendance` (database view without proper @Id)

## Rollback

If something goes wrong, you can rollback using git:

```bash
git reset --hard HEAD
```

Or restore specific files:
```bash
git checkout -- src/main/java/com/unnathy/fieldwise/entity/*.java
```

## Expected Output

```
======================================================================
Entity Refactoring Script
======================================================================

Scanning directory: D:\workspace\field-wise\FieldWise\src\main\java\com\unnathy\fieldwise\entity

Found 45 entity files

----------------------------------------------------------------------
✓ Activity                     Modified successfully
✓ Attendance                   Modified successfully
...
⊘ BaseEntity                   SKIPPED (excluded)
...
----------------------------------------------------------------------

Summary:
  Total files found:     45
  Processed:             42
  Modified:              42
  Skipped:               3
  Errors:                0

======================================================================

✓ Refactoring completed successfully!

Next steps:
  1. Review the changes in your IDE
  2. Run your build to ensure no compilation errors
  3. Run your tests to ensure functionality is preserved
```

## Troubleshooting

### Script doesn't run
- Ensure Python 3.6+ is installed: `python --version`
- Check file permissions

### Compilation errors after refactoring
- Check if any entity had custom implementations of removed fields
- Verify imports are correct
- Review entities that might have had different ID types (should all be Long)

### Need to modify specific entities manually
If some entities need special handling:
1. Restore them from git: `git checkout -- path/to/Entity.java`
2. Manually edit as needed
3. Ensure they still extend `BaseEntity`

---

## 2. DTO Generation (generate_dtos.py)

Automatically generates Data Transfer Object (DTO) classes for all entities.

### What it Does

- Creates a `BaseDTO` class with common fields
- Generates DTOs for all entities
- Converts relationships to ID fields (e.g., `Shop shop` → `Long shopId`)
- Adds Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`)
- Skips collection relationships by default

### Usage

```bash
cd utils
python generate_dtos.py
```

### Generated Output

```
src/main/java/com/unnathy/fieldwise/
└── dto/
    ├── BaseDTO.java
    ├── OrderDTO.java
    ├── ProductDTO.java
    └── ... (one DTO per entity)
```

### Example Generated DTO

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

### Next Steps After Generation

1. Review generated DTOs
2. Customize as needed (add nested DTOs, computed fields, etc.)
3. Create mappers (MapStruct or manual)
4. Use DTOs in controllers and services

**For detailed documentation, see [DTO_GENERATION_README.md](DTO_GENERATION_README.md)**

---

## General Tips

- Always commit your code before running these scripts
- Review changes before committing
- Run your build and tests after generation
- These scripts use only Python standard library (no dependencies needed)
