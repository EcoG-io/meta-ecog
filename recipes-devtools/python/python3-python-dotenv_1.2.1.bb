SUMMARY = "Read key-value pairs from a .env file and set them as environment variables"
HOMEPAGE = "https://github.com/theskumar/python-dotenv"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e914cdb773ae44a732b392532d88f072"

PYPI_PACKAGE = "python_dotenv"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "42667e897e16ab0d66954af0e60a9caa94f0fd4ecf3aaf6d2d260eec1aa36ad6"

do_configure:prepend() {
    sed -i 's|^license = "\(.*\)"|license = {text = "\1"}|' ${S}/pyproject.toml
    sed -i '/^license-files/d' ${S}/pyproject.toml
    sed -i 's|setuptools >= 77\.0|setuptools >= 40.0|' ${S}/pyproject.toml
}

RDEPENDS:${PN} += "python3-core python3-io"

