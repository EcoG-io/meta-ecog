SUMMARY = "JSON Referencing + Python"
HOMEPAGE = "https://github.com/python-jsonschema/referencing"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=93eb9740964b59e9ba30281255b044e2"

DEPENDS += "python3-hatch-vcs-native"

inherit pypi python_hatchling

do_configure:prepend() {
    sed -i '/^license-files/d' ${S}/pyproject.toml
}

SRC_URI[sha256sum] = "44aefc3142c5b842538163acb373e24cce6632bd54bdb01b21ad5863489f50d8"

RDEPENDS:${PN} += " \
    python3-attrs \
    python3-rpds-py \
"

