SUMMARY = "C parser in Python"
HOMEPAGE = "https://github.com/eliben/pycparser"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9761c3ffee7ba99c60dca0408fd3262b"

inherit pypi setuptools3

SRC_URI[sha256sum] = "78816d4f24add8f10a06d6f05b4d424ad9e96cfebf68a4ddc99c65c0720d00c2"

BBCLASSEXTEND = "native nativesdk"

