#!/usr/bin/env python3
"""
Cleanup entity and DTO classes by removing relationship annotations and
converting relation fields to Long ids.
"""

from __future__ import annotations

import re
from pathlib import Path


RE_RELATION_ANN = re.compile(r"^\s*@(?:ManyToOne|OneToMany|ManyToMany)\b.*$", re.MULTILINE)
RE_JOIN_COLUMN = re.compile(r"@JoinColumn(\s*\([^)]*\))")
RE_FIELD = re.compile(r"^(\s*private)\s+([A-Za-z0-9_<>, ?]+)\s+([A-Za-z0-9_]+)\s*;")
RE_COLLECTION = re.compile(r"\b(List|Set|Collection)\s*<[^>]+>")


def _convert_relation_field(line: str) -> str:
    match = RE_FIELD.match(line)
    if not match:
        return line
    prefix, type_name, var_name = match.groups()
    if RE_COLLECTION.search(type_name):
        new_type = RE_COLLECTION.sub(r"\1<Long>", type_name)
    else:
        new_type = "Long"
    return f"{prefix} {new_type} {var_name};"


def _process_java_file(path: Path) -> tuple[bool, str]:
    original = path.read_text(encoding="utf-8")
    lines = original.splitlines()
    modified = False

    output_lines: list[str] = []
    relation_pending = False

    for line in lines:
        if RE_RELATION_ANN.match(line):
            relation_pending = True
            modified = True
            continue

        if "@JoinColumn" in line:
            new_line = RE_JOIN_COLUMN.sub(r"@Column\1", line)
            if new_line != line:
                modified = True
            line = new_line

        if relation_pending:
            if RE_FIELD.match(line):
                new_line = _convert_relation_field(line)
                if new_line != line:
                    modified = True
                output_lines.append(new_line)
                relation_pending = False
                continue
            output_lines.append(line)
            continue

        output_lines.append(line)

    if modified and output_lines:
        path.write_text("\n".join(output_lines) + "\n", encoding="utf-8")
        return True, "modified"

    return False, "no changes"


def _iter_java_files(root: Path) -> list[Path]:
    if not root.exists():
        return []
    return sorted(root.glob("*.java"))


def main() -> None:
    repo_root = Path(__file__).resolve().parents[1]
    entity_dir = repo_root / "src/main/java/com/unnathy/fieldwise/entity"
    dto_dir = repo_root / "src/main/java/com/unnathy/fieldwise/dto"

    entity_files = _iter_java_files(entity_dir)
    dto_files = _iter_java_files(dto_dir)

    total = 0
    modified = 0

    for file_path in entity_files + dto_files:
        total += 1
        changed, _ = _process_java_file(file_path)
        if changed:
            modified += 1

    print(f"Processed {total} files")
    print(f"Modified {modified} files")


if __name__ == "__main__":
    main()
