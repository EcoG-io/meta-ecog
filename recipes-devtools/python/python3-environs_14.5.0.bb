SUMMARY = "Simplified environment variable parsing"
HOMEPAGE = "https://github.com/sloria/environs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5731ba2b61489927637effa6cf06ff6b"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "f7b8f6fcf3301bc674bc9c03e39b5986d116126ffb96764efd34c339ed9464ee"

RDEPENDS:${PN} += " \
    python3-marshmallow \
    python3-python-dotenv \
"

