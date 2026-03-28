SUMMARY = "This provides a PEP 517 build backend for packages using Flit."
HOMEPAGE = "https://github.com/pypa/flit"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41eb78fa8a872983a882c694a8305f08"

# flit_core is distributed inside the flit tarball
PYPI_PACKAGE = "flit"

SRC_URI[sha256sum] = "1c80f34dd96992e7758b40423d2809f48f640ca285d0b7821825e50745ec3740"

inherit pypi python_flit_core

PEP517_SOURCE_PATH = "${S}/flit_core"

# Break the dependency loop for native bootstrap build
DEPENDS:remove:class-native = " python3-build-native python3-installer-native"
DEPENDS:append:class-native = " unzip-native"

do_compile:class-native() {
    python_flit_core_do_manual_build
}

do_install:class-native() {
    python_pep517_do_bootstrap_install
}

PACKAGES =+ "${PN}-tests"
FILES:${PN}-tests += "${PYTHON_SITEPACKAGES_DIR}/flit_core/tests/*"

BBCLASSEXTEND = "native nativesdk"
