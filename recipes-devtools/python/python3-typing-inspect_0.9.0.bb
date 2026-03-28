SUMMARY = "Runtime inspection utilities for Python typing module"
HOMEPAGE = "https://github.com/ilevkivskyi/typing_inspect"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38939e40df14ccacab135b023198167a"

PYPI_PACKAGE = "typing_inspect"

inherit pypi setuptools3

SRC_URI[sha256sum] = "b23fc42ff6f6ef6954e4852c1fb512cdd18dbea03134f91f856a95ccc9461f78"

RDEPENDS:${PN} += " \
    python3-mypy-extensions \
    python3-typing-extensions \
"

