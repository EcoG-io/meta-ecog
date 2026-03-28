SUMMARY = "Python SQL toolkit and Object Relational Mapper"
HOMEPAGE = "http://www.sqlalchemy.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=061025f14213ac2818ff353223d6eca6"

PYPI_PACKAGE = "sqlalchemy"

inherit pypi setuptools3

SRC_URI[sha256sum] = "0ae7454e1ab1d780aee69fd2aae7d6b8670a581d8847f2d1e0f7ddfbf47e5a22"

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-compression \
    python3-greenlet \
    python3-json \
    python3-logging \
    python3-typing-extensions \
"

