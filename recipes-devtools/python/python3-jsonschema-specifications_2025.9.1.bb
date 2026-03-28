SUMMARY = "JSON Schema meta-schemas and vocabularies, exposed as a referencing-based Registry"
HOMEPAGE = "https://github.com/python-jsonschema/jsonschema-specifications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=93eb9740964b59e9ba30281255b044e2"

PYPI_PACKAGE = "jsonschema_specifications"

DEPENDS += "python3-hatch-vcs-native"

inherit pypi python_hatchling

SRC_URI[sha256sum] = "b540987f239e745613c7a9176f3edb72b832a4ac465cf02712288397832b5e8d"

do_configure:prepend() {
    sed -i '/^license-files/,/^\]/d' ${S}/pyproject.toml
    sed -i '/^license-files/d' ${S}/pyproject.toml
    sed -i 's|hatchling>=1\.[0-9]*\.[0-9]*|hatchling>=1.25.0|' ${S}/pyproject.toml
}

RDEPENDS:${PN} += "python3-referencing"

