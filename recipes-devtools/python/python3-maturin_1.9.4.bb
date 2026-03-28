SUMMARY = "Build and publish crates with pyo3, rust-cpython, cffi bindings and rust binaries as python packages"
HOMEPAGE = "https://github.com/pyo3/maturin"
SECTION = "devel/python"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = "file://license-apache;md5=1836efb2eb779966696f473ee8540542 \
                    file://license-mit;md5=85fd3b67069cff784d98ebfc7d5c0797"

SRC_URI[sha256sum] = "235163a0c99bc6f380fb8786c04fd14dcf6cd622ff295ea3de525015e6ac40cf"

S = "${WORKDIR}/maturin-${PV}"

# OE-Core has setuptools 72.1.0 and setuptools-rust 1.11.1; lower build requirements
do_configure:prepend() {
    python3 -c "
import re
with open('${S}/pyproject.toml', 'r') as f:
    content = f.read()
content = content.replace('setuptools>=77.0.0', 'setuptools>=72.0.0')
content = content.replace('setuptools-rust>=1.11.0', 'setuptools-rust>=1.10.0')
# Remove license-files multiline array (fresh file)
content = re.sub(r'license-files\s*=\s*\[[^\]]*\]', '', content, flags=re.DOTALL)
# Remove orphan array lines if license-files key was already stripped (partial edit)
content = re.sub(r'(license = \{[^\n]+\})\n((?:\s+\"[^\n]+\",?\n)+)\]', r'\1\n', content)
# Convert PEP 639 license string to dict form
content = re.sub(r'^license = \"(.+)\"', r'license = {text = \"\1\"}', content, flags=re.MULTILINE)
with open('${S}/pyproject.toml', 'w') as f:
    f.write(content)
"
}

CFLAGS += "-fdebug-prefix-map=${CARGO_HOME}=${TARGET_DBGSRC_DIR}/cargo_home"

DEPENDS += "\
    python3-setuptools-rust-native \
    python3-semantic-version-native \
    python3-setuptools-rust \
"

require ${BPN}-crates.inc

inherit pypi cargo-update-recipe-crates python_pyo3 python_setuptools_build_meta

do_configure() {
    python_pyo3_do_configure
    cargo_common_do_configure
    python_pep517_do_configure
}

RDEPENDS:${PN} += "\
    cargo \
    python3-json \
    rust \
"

RRECOMMENDS:${PN} += "\
    python3-ensurepip \
    python3-pip \
    python3-venv \
"

BBCLASSEXTEND = "native nativesdk"
