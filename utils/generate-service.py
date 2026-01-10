#!/usr/bin/env python3
"""
Service Generation Script
Generates service classes for all entity/DTO pairs with simple mappings.
"""

import argparse
import re
from pathlib import Path
from typing import List, Tuple


ROOT_DIR = Path(__file__).resolve().parents[1]
ENTITY_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "entity"
DTO_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "dto"
REPO_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "repo"
SERVICE_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "service"

SKIP_ENTITIES = {"BaseEntity", "VActiveUser", "VPendingApproval", "VTodayAttendance"}
BASE_FIELDS = ["id", "createdAt", "updatedAt"]


def parse_fields(java_path: Path) -> List[str]:
    fields = []
    content = java_path.read_text(encoding="utf-8")
    for line in content.splitlines():
        stripped = line.strip()
        if not stripped.startswith("private "):
            continue
        if " static " in stripped:
            continue
        match = re.search(r"private\s+[\w<>, ?]+\s+(\w+);", stripped)
        if match:
            fields.append(match.group(1))
    return fields


def capitalize(name: str) -> str:
    if not name:
        return name
    return name[0].upper() + name[1:]


def build_mappings(dto_fields: List[str], entity_fields: List[str]) -> Tuple[List[Tuple[str, str]], List[Tuple[str, str]]]:
    entity_set = set(entity_fields)
    dto_set = set(dto_fields)

    dto_to_entity = []
    for dto_field in dto_fields:
        if dto_field in entity_set:
            dto_to_entity.append((dto_field, dto_field))
        elif dto_field.endswith("Id"):
            entity_field = dto_field[:-2]
            if entity_field in entity_set:
                dto_to_entity.append((dto_field, entity_field))

    entity_to_dto = []
    for entity_field in entity_fields:
        if entity_field in dto_set:
            entity_to_dto.append((entity_field, entity_field))
        else:
            dto_field = entity_field + "Id"
            if dto_field in dto_set:
                entity_to_dto.append((entity_field, dto_field))

    return dto_to_entity, entity_to_dto


def generate_service_content(entity_name: str, dto_name: str, repo_name: str,
                             dto_to_entity: List[Tuple[str, str]], entity_to_dto: List[Tuple[str, str]]) -> str:
    service_name = f"{entity_name}Service"

    lines = [
        "package com.unnathy.fieldwise.service;",
        "",
        "import com.unnathy.fieldwise.core.BasicEntityService;",
        "import com.unnathy.fieldwise.core.UnnathyError;",
        f"import com.unnathy.fieldwise.dto.{dto_name};",
        f"import com.unnathy.fieldwise.entity.{entity_name};",
        "import com.unnathy.fieldwise.entity.User;",
        f"import com.unnathy.fieldwise.repo.{repo_name};",
        "import java.util.List;",
        "import java.util.stream.Collectors;",
        "import lombok.RequiredArgsConstructor;",
        "import org.springframework.stereotype.Service;",
        "",
        "@Service",
        "@RequiredArgsConstructor",
        f"public class {service_name} implements BasicEntityService<{dto_name}, Long> {{",
        "",
        f"    private final {repo_name} repository;",
        "",
        "    @Override",
        f"    public {dto_name} post({dto_name} data, String authorization, User principal) throws UnnathyError {{",
        f"        {entity_name} entity = toEntity(data);",
        f"        {entity_name} saved = repository.save(entity);",
        "        return toDto(saved);",
        "    }",
        "",
        "    @Override",
        f"    public {dto_name} patch({dto_name} data, String authorization, User principal) throws UnnathyError {{",
        "        return put(data, authorization, principal);",
        "    }",
        "",
        "    @Override",
        f"    public {dto_name} put({dto_name} data, String authorization, User principal) throws UnnathyError {{",
        f"        {entity_name} entity = toEntity(data);",
        f"        {entity_name} saved = repository.save(entity);",
        "        return toDto(saved);",
        "    }",
        "",
        "    @Override",
        f"    public List<{dto_name}> get(String authorization, User principal) throws UnnathyError {{",
        "        return repository.findAll().stream()",
        "                .map(this::toDto)",
        "                .collect(Collectors.toList());",
        "    }",
        "",
        "    @Override",
        f"    public {dto_name} getWithId(String authorization, User principal, Long id) throws UnnathyError {{",
        "        return repository.findById(id)",
        "                .map(this::toDto)",
        f"                .orElseThrow(() -> new UnnathyError(\"NOT_FOUND\", \"{entity_name} not found\", null));",
        "    }",
        "",
        f"    private {dto_name} toDto({entity_name} entity) {{",
        "        if (entity == null) {",
        "            return null;",
        "        }",
        f"        {dto_name} dto = new {dto_name}();",
    ]

    for entity_field, dto_field in entity_to_dto:
        lines.append(f"        dto.set{capitalize(dto_field)}(entity.get{capitalize(entity_field)}());")

    lines.extend([
        "        return dto;",
        "    }",
        "",
        f"    private {entity_name} toEntity({dto_name} data) {{",
        "        if (data == null) {",
        "            return null;",
        "        }",
        f"        {entity_name} entity = new {entity_name}();",
    ])

    for dto_field, entity_field in dto_to_entity:
        lines.append(f"        entity.set{capitalize(entity_field)}(data.get{capitalize(dto_field)}());")

    lines.extend([
        "        return entity;",
        "    }",
        "}",
        "",
    ])

    return "\n".join(lines)


def main() -> int:
    parser = argparse.ArgumentParser(description="Generate services for entity/DTO pairs.")
    parser.add_argument("--overwrite", action="store_true", help="Overwrite existing service files.")
    args = parser.parse_args()

    SERVICE_DIR.mkdir(parents=True, exist_ok=True)

    dto_files = sorted(DTO_DIR.glob("*DTO.java"))
    if not dto_files:
        print("No DTO files found.")
        return 1

    processed = 0
    created = 0
    skipped = 0

    for dto_path in dto_files:
        dto_name = dto_path.stem
        entity_name = dto_name[:-3] if dto_name.endswith("DTO") else None
        if not entity_name or entity_name in SKIP_ENTITIES:
            skipped += 1
            continue

        entity_path = ENTITY_DIR / f"{entity_name}.java"
        repo_name = f"{entity_name}Repository"
        repo_path = REPO_DIR / f"{repo_name}.java"

        if not entity_path.exists() or not repo_path.exists():
            skipped += 1
            continue

        service_path = SERVICE_DIR / f"{entity_name}Service.java"
        if service_path.exists() and not args.overwrite:
            skipped += 1
            continue

        dto_fields = BASE_FIELDS + parse_fields(dto_path)
        entity_fields = BASE_FIELDS + parse_fields(entity_path)
        dto_to_entity, entity_to_dto = build_mappings(dto_fields, entity_fields)

        content = generate_service_content(entity_name, dto_name, repo_name, dto_to_entity, entity_to_dto)
        service_path.write_text(content, encoding="utf-8")
        processed += 1
        created += 1

    print("Service generation completed.")
    print(f"Processed: {processed}")
    print(f"Created:   {created}")
    print(f"Skipped:   {skipped}")
    print(f"Output:    {SERVICE_DIR}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
