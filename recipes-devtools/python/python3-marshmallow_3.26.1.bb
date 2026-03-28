SUMMARY = "Simplified object serialization in Python"
HOMEPAGE = "https://github.com/marshmallow-code/marshmallow"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=27586b20700d7544c06933afe56f7df4"

inherit pypi python_flit_core

SRC_URI[sha256sum] = "e6d8affb6cb61d39d26402096dc0aee12d5a26d490a121f118d2e81dc0719dc6"

RDEPENDS:${PN} += " \
    python3-compression \
    python3-datetime \
    python3-email \
    python3-json \
    python3-numbers \
    python3-packaging \
"

