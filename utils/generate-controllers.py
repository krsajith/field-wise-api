#!/usr/bin/env python3
"""
Controller Generation Script
Generates controller classes for all entity/DTO pairs.
"""

from pathlib import Path


ROOT_DIR = Path(__file__).resolve().parents[1]
DTO_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "dto"
SERVICE_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "service"
CONTROLLER_DIR = ROOT_DIR / "src" / "main" / "java" / "com" / "unnathy" / "fieldwise" / "controller"

SKIP_ENTITIES = {"BaseEntity", "VActiveUser", "VPendingApproval", "VTodayAttendance"}


def lower_camel(name: str) -> str:
    if not name:
        return name
    return name[0].lower() + name[1:]


def build_path(entity_name: str) -> str:
    return f"/api/{lower_camel(entity_name)}s"


def generate_controller_content(entity_name: str, dto_name: str) -> str:
    controller_name = f"{entity_name}Controller"
    service_name = f"{entity_name}Service"
    request_path = build_path(entity_name)

    lines = [
        "package com.unnathy.fieldwise.controller;",
        "",
        "import com.unnathy.fieldwise.core.BaseController;",
        "import com.unnathy.fieldwise.core.BasicEntityService;",
        f"import com.unnathy.fieldwise.dto.{dto_name};",
        f"import com.unnathy.fieldwise.service.{service_name};",
        "import lombok.RequiredArgsConstructor;",
        "import org.springframework.web.bind.annotation.RequestMapping;",
        "import org.springframework.web.bind.annotation.RestController;",
        "",
        "@RestController",
        f"@RequestMapping(\"{request_path}\")",
        "@RequiredArgsConstructor",
        f"public class {controller_name} implements BaseController<{dto_name}, Long> {{",
        "",
        f"    private final {service_name} service;",
        "",
        "    @Override",
        f"    public BasicEntityService<{dto_name}, Long> getService() {{",
        "        return service;",
        "    }",
        "}",
        "",
    ]

    return "\n".join(lines)


def main() -> int:
    CONTROLLER_DIR.mkdir(parents=True, exist_ok=True)

    dto_files = sorted(DTO_DIR.glob("*DTO.java"))
    if not dto_files:
        print("No DTO files found.")
        return 1

    created = 0
    skipped = 0

    for dto_path in dto_files:
        dto_name = dto_path.stem
        entity_name = dto_name[:-3] if dto_name.endswith("DTO") else None
        if not entity_name or entity_name in SKIP_ENTITIES:
            skipped += 1
            continue

        service_path = SERVICE_DIR / f"{entity_name}Service.java"
        if not service_path.exists():
            skipped += 1
            continue

        controller_path = CONTROLLER_DIR / f"{entity_name}Controller.java"
        if controller_path.exists():
            skipped += 1
            continue

        content = generate_controller_content(entity_name, dto_name)
        controller_path.write_text(content, encoding="utf-8")
        created += 1

    print("Controller generation completed.")
    print(f"Created: {created}")
    print(f"Skipped: {skipped}")
    print(f"Output:  {CONTROLLER_DIR}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
