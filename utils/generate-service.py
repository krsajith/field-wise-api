#!/usr/bin/env python3
"""
Service Generation Script
Generates service classes for all entity/DTO pairs with simple mappings.
"""

import argparse
from pathlib import Path


ROOT_DIR = Path(__file__).resolve().parents[1]
ENTITY_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "entity"
DTO_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "dto"
REPO_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "repo"
SERVICE_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "service"

SKIP_ENTITIES = {"BaseEntity", "VActiveUser", "VPendingApproval", "VTodayAttendance"}
def generate_service_content(entity_name: str, dto_name: str, repo_name: str) -> str:
    service_name = f"{entity_name}Service"

    lines = [
        "package com.unnathy.fieldwise.service;",
        "",
        "import com.unnathy.fieldwise.core.BasicEntityService;",
        "import com.unnathy.fieldwise.core.ModelMapperService;",
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
        "    private final ModelMapperService modelMapperService;",
        "",
        "    @Override",
        f"    public {dto_name} post({dto_name} data, String authorization, User principal) throws UnnathyError {{",
        f"        {entity_name} entity = modelMapperService.map(data, {entity_name}.class);",
        f"        {entity_name} saved = repository.save(entity);",
        f"        return modelMapperService.map(saved, {dto_name}.class);",
        "    }",
        "",
        "    @Override",
        f"    public {dto_name} patch({dto_name} data, String authorization, User principal) throws UnnathyError {{",
        "        return put(data, authorization, principal);",
        "    }",
        "",
        "    @Override",
        f"    public {dto_name} put({dto_name} data, String authorization, User principal) throws UnnathyError {{",
        f"        {entity_name} entity = modelMapperService.map(data, {entity_name}.class);",
        f"        {entity_name} saved = repository.save(entity);",
        f"        return modelMapperService.map(saved, {dto_name}.class);",
        "    }",
        "",
        "    @Override",
        f"    public List<{dto_name}> get(String authorization, User principal) throws UnnathyError {{",
        "        return repository.findAll().stream()",
        f"                .map(entity -> modelMapperService.map(entity, {dto_name}.class))",
        "                .collect(Collectors.toList());",
        "    }",
        "",
        "    @Override",
        f"    public {dto_name} getWithId(String authorization, User principal, Long id) throws UnnathyError {{",
        "        return repository.findById(id)",
        f"                .map(entity -> modelMapperService.map(entity, {dto_name}.class))",
        f"                .orElseThrow(() -> new UnnathyError(\"NOT_FOUND\", \"{entity_name} not found\", null));",
        "    }",
        "}",
        "",
    ]

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

        content = generate_service_content(entity_name, dto_name, repo_name)
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
