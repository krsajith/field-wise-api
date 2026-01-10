#!/usr/bin/env python3
"""
DTO Generation Script
Generates DTO (Data Transfer Object) classes for all entities.
"""

import os
import re
from pathlib import Path
from typing import List, Tuple, Dict

# Configuration
ENTITY_DIR = Path(r"D:\workspace\field-wise\FieldWise\src\main\java\com\unnathy\fieldwise\entity")
DTO_DIR = Path(r"D:\workspace\field-wise\FieldWise\src\main\java\com\unnathy\fieldwise\dto")
SKIP_ENTITIES = ["BaseEntity", "VActiveUser", "VPendingApproval", "VTodayAttendance"]

# Field type mappings for simple types
SIMPLE_TYPES = {
    'String', 'Long', 'Integer', 'Boolean', 'BigDecimal', 'Double',
    'Float', 'Short', 'Byte', 'LocalDate', 'LocalDateTime', 'Instant',
    'LocalTime', 'Date', 'UUID'
}

# Stats
stats = {
    "processed": 0,
    "created": 0,
    "skipped": 0,
    "errors": 0
}


class FieldInfo:
    def __init__(self, name, field_type, is_relationship=False, relationship_type=None):
        self.name = name
        self.field_type = field_type
        self.is_relationship = is_relationship
        self.relationship_type = relationship_type


def parse_entity_fields(content: str, entity_name: str) -> List[FieldInfo]:
    """
    Parse entity file and extract field information.
    """
    fields = []

    # Split into lines for processing
    lines = content.split('\n')
    i = 0

    while i < len(lines):
        line = lines[i].strip()

        # Check for relationship annotations
        is_relationship = False
        relationship_type = None

        if '@ManyToOne' in line or '@OneToOne' in line:
            is_relationship = True
            relationship_type = 'ManyToOne' if '@ManyToOne' in line else 'OneToOne'

            # Find the field declaration (could be a few lines down)
            j = i + 1
            while j < len(lines) and 'private' not in lines[j]:
                j += 1

            if j < len(lines):
                field_line = lines[j].strip()
                match = re.search(r'private\s+(\w+)\s+(\w+);', field_line)
                if match:
                    related_type = match.group(1)
                    field_name = match.group(2)
                    fields.append(FieldInfo(field_name, related_type, True, relationship_type))

            i = j + 1
            continue

        # Check for @OneToMany or @ManyToMany (skip these in DTOs by default)
        if '@OneToMany' in line or '@ManyToMany' in line:
            # Skip to next field
            while i < len(lines) and ';' not in lines[i]:
                i += 1
            i += 1
            continue

        # Regular field
        if line.startswith('private '):
            match = re.search(r'private\s+(\w+(?:<.*?>)?)\s+(\w+);', line)
            if match:
                field_type = match.group(1)
                field_name = match.group(2)

                # Skip if it's from BaseEntity
                if field_name in ['id', 'createdAt', 'updatedAt']:
                    i += 1
                    continue

                fields.append(FieldInfo(field_name, field_type, False, None))

        i += 1

    return fields


def generate_dto_content(entity_name: str, fields: List[FieldInfo]) -> str:
    """
    Generate DTO class content.
    """
    dto_name = f"{entity_name}DTO"

    # Collect imports
    imports = set()
    imports.add("lombok.Data")
    imports.add("lombok.NoArgsConstructor")
    imports.add("lombok.AllArgsConstructor")

    # Generate fields
    field_declarations = []

    for field in fields:
        if field.is_relationship:
            # Convert relationship to ID field
            id_field_name = field.name + "Id"
            field_declarations.append(f"    private Long {id_field_name};")

            # Optionally add the related entity name for clarity
            # field_declarations.append(f"    private String {field.name}Name;")
        else:
            # Check for special imports
            if 'BigDecimal' in field.field_type:
                imports.add("java.math.BigDecimal")
            elif 'LocalDate' in field.field_type and 'LocalDateTime' not in field.field_type:
                imports.add("java.time.LocalDate")
            elif 'LocalDateTime' in field.field_type:
                imports.add("java.time.LocalDateTime")
            elif 'Instant' in field.field_type:
                imports.add("java.time.Instant")
            elif 'LocalTime' in field.field_type:
                imports.add("java.time.LocalTime")

            field_declarations.append(f"    private {field.field_type} {field.name};")

    # Build the class content
    content_parts = [
        "package com.unnathy.fieldwise.dto;",
        "",
    ]

    # Add imports
    for imp in sorted(imports):
        content_parts.append(f"import {imp};")

    content_parts.extend([
        "",
        "@Data",
        "@NoArgsConstructor",
        "@AllArgsConstructor",
        f"public class {dto_name} extends BaseDTO {{",
        "",
    ])

    # Add fields
    content_parts.extend(field_declarations)

    content_parts.extend([
        "}",
        ""
    ])

    return "\n".join(content_parts)


def generate_base_dto() -> str:
    """
    Generate BaseDTO class content.
    """
    return """package com.unnathy.fieldwise.dto;

import lombok.Data;

import java.time.Instant;

@Data
public abstract class BaseDTO {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
}
"""


def process_entity(entity_path: Path) -> Tuple[bool, str]:
    """
    Process a single entity and generate its DTO.
    """
    try:
        entity_name = entity_path.stem

        # Read entity file
        with open(entity_path, 'r', encoding='utf-8') as f:
            content = f.read()

        # Parse fields
        fields = parse_entity_fields(content, entity_name)

        # Generate DTO content
        dto_content = generate_dto_content(entity_name, fields)

        # Write DTO file
        dto_file = DTO_DIR / f"{entity_name}DTO.java"
        with open(dto_file, 'w', encoding='utf-8') as f:
            f.write(dto_content)

        stats["created"] += 1
        return True, f"Created {entity_name}DTO.java"

    except Exception as e:
        stats["errors"] += 1
        return False, f"Error: {str(e)}"


def main():
    """
    Main DTO generation process.
    """
    print("=" * 70)
    print("DTO Generation Script")
    print("=" * 70)
    print(f"\nEntity directory: {ENTITY_DIR}")
    print(f"DTO directory:    {DTO_DIR}\n")

    # Create DTO directory if it doesn't exist
    DTO_DIR.mkdir(parents=True, exist_ok=True)
    print(f"✓ Created DTO directory\n")

    # Generate BaseDTO first
    base_dto_path = DTO_DIR / "BaseDTO.java"
    with open(base_dto_path, 'w', encoding='utf-8') as f:
        f.write(generate_base_dto())
    print(f"✓ Created BaseDTO.java\n")

    # Find all entity files
    entity_files = list(ENTITY_DIR.glob("*.java"))
    print(f"Found {len(entity_files)} entity files\n")
    print("-" * 70)

    # Process each entity
    for entity_path in sorted(entity_files):
        entity_name = entity_path.stem

        # Skip certain entities
        if entity_name in SKIP_ENTITIES:
            print(f"⊘ {entity_name:<30} SKIPPED")
            stats["skipped"] += 1
            continue

        # Process the entity
        success, message = process_entity(entity_path)
        stats["processed"] += 1

        if success:
            print(f"✓ {entity_name:<30} {message}")
        else:
            print(f"✗ {entity_name:<30} {message}")

    # Print summary
    print("-" * 70)
    print("\nSummary:")
    print(f"  Total entities found:  {len(entity_files)}")
    print(f"  Processed:             {stats['processed']}")
    print(f"  DTOs created:          {stats['created'] + 1}")  # +1 for BaseDTO
    print(f"  Skipped:               {stats['skipped']}")
    print(f"  Errors:                {stats['errors']}")
    print("\n" + "=" * 70)

    if stats['created'] > 0:
        print("\n✓ DTO generation completed successfully!")
        print(f"\nDTOs created in: {DTO_DIR}")
        print("\nNext steps:")
        print("  1. Review generated DTOs")
        print("  2. Add custom fields or nested DTOs as needed")
        print("  3. Create mapper classes (manually or with MapStruct)")
        print("  4. Use DTOs in your service/controller layers")
    else:
        print("\n○ No DTOs were created.")


if __name__ == "__main__":
    main()
