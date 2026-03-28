SUMMARY = "Easily serialize dataclasses to and from JSON"
HOMEPAGE = "https://github.com/lidatong/dataclasses-json"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c1876b666ebd26c12461357f868c812"

PYPI_PACKAGE = "dataclasses_json"

inherit pypi python_poetry_core

# pyproject.toml uses poetry-dynamic-versioning as build backend, but it is
# disabled (enable = false). Patch it to use plain poetry-core so we don't
# need poetry-dynamic-versioning in the native sysroot.
do_configure:prepend() {
    sed -i \
        -e 's|requires = \["poetry-core.*|requires = ["poetry-core>=1.2.0"]|' \
        -e 's|build-backend = "poetry_dynamic_versioning.backend"|build-backend = "poetry.core.masonry.api"|' \
        ${S}/pyproject.toml
}

SRC_URI[sha256sum] = "b6b3e528266ea45b9535223bc53ca645f5208833c29229e847b3f26a1cc55fc0"

RDEPENDS:${PN} += " \
    python3-marshmallow \
    python3-typing-inspect \
    python3-typing-extensions \
"

