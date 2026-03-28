SUMMARY = "Experimental type system extensions for programs checked with mypy"
HOMEPAGE = "https://github.com/python/mypy_extensions"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0fe3219e2470a78c0d1837019b8b426e"

PYPI_PACKAGE = "mypy_extensions"

inherit pypi python_setuptools_build_meta

do_configure:prepend() {
    if [ -f ${S}/pyproject.toml ]; then
        sed -i 's|^license = "\(.*\)"|license = {text = "\1"}|' ${S}/pyproject.toml
        sed -i '/^license-files/d' ${S}/pyproject.toml
    fi
}

SRC_URI[sha256sum] = "52e68efc3284861e772bbcd66823fde5ae21fd2fdb51c62a211403730b916558"

