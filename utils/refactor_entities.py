#!/usr/bin/env python3
"""
Entity Refactoring Script
Refactors entity classes to extend BaseEntity and removes common fields.
"""

import os
import re
from pathlib import Path

# Configuration
ENTITY_DIR = Path(r"D:\workspace\field-wise\FieldWise\src\main\java\com\unnathy\fieldwise\entity")
SKIP_ENTITIES = ["BaseEntity", "VActiveUser", "VPendingApproval", "VTodayAttendance"]

# Stats
stats = {
    "processed": 0,
    "skipped": 0,
    "modified": 0,
    "errors": 0
}


def remove_field_block(content, field_name):
    """
    Remove a field declaration along with its annotations.
    Handles multi-line annotations and field declarations.
    """
    # Pattern to match field with annotations (supports multiple lines)
    # Captures annotations like @Id, @GeneratedValue, @Column, @ColumnDefault
    pattern = rf'(?:^\s*@.*\n)*^\s*private\s+\w+\s+{field_name};.*\n'

    # Remove the field and its annotations
    modified_content = re.sub(pattern, '', content, flags=re.MULTILINE)

    # Also remove any orphaned blank lines (more than 2 consecutive)
    modified_content = re.sub(r'\n\n\n+', '\n\n', modified_content)

    return modified_content


def add_base_entity_extension(content):
    """
    Add 'extends BaseEntity' to the class declaration.
    """
    # Pattern to match class declaration
    class_pattern = r'(public\s+class\s+\w+)(\s*\{)'

    # Check if already extends something
    if re.search(r'public\s+class\s+\w+\s+extends\s+', content):
        return content, False

    # Add extends BaseEntity
    modified_content = re.sub(
        class_pattern,
        r'\1 extends BaseEntity\2',
        content
    )

    return modified_content, True


def refactor_entity(file_path):
    """
    Refactor a single entity file.
    """
    try:
        # Read the file
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()

        original_content = content
        modified = False

        # Remove common fields
        fields_to_remove = ['id', 'createdAt', 'updatedAt']
        for field in fields_to_remove:
            new_content = remove_field_block(content, field)
            if new_content != content:
                modified = True
                content = new_content

        # Add extends BaseEntity
        content, extended = add_base_entity_extension(content)
        if extended:
            modified = True

        # Write back if modified
        if modified:
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            stats["modified"] += 1
            return True, "Modified successfully"
        else:
            return False, "No changes needed"

    except Exception as e:
        stats["errors"] += 1
        return False, f"Error: {str(e)}"


def main():
    """
    Main refactoring process.
    """
    print("=" * 70)
    print("Entity Refactoring Script")
    print("=" * 70)
    print(f"\nScanning directory: {ENTITY_DIR}\n")

    # Find all Java files in entity directory
    entity_files = list(ENTITY_DIR.glob("*.java"))

    print(f"Found {len(entity_files)} entity files\n")
    print("-" * 70)

    # Process each file
    for file_path in sorted(entity_files):
        entity_name = file_path.stem

        # Skip certain entities
        if entity_name in SKIP_ENTITIES:
            print(f"⊘ {entity_name:<30} SKIPPED (excluded)")
            stats["skipped"] += 1
            continue

        # Refactor the entity
        success, message = refactor_entity(file_path)
        stats["processed"] += 1

        if success:
            print(f"✓ {entity_name:<30} {message}")
        else:
            print(f"○ {entity_name:<30} {message}")

    # Print summary
    print("-" * 70)
    print("\nSummary:")
    print(f"  Total files found:     {len(entity_files)}")
    print(f"  Processed:             {stats['processed']}")
    print(f"  Modified:              {stats['modified']}")
    print(f"  Skipped:               {stats['skipped']}")
    print(f"  Errors:                {stats['errors']}")
    print("\n" + "=" * 70)

    if stats['modified'] > 0:
        print("\n✓ Refactoring completed successfully!")
        print("\nNext steps:")
        print("  1. Review the changes in your IDE")
        print("  2. Run your build to ensure no compilation errors")
        print("  3. Run your tests to ensure functionality is preserved")
    else:
        print("\n○ No entities were modified.")


if __name__ == "__main__":
    main()
