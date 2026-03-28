SUMMARY = "Amazon S3 Transfer Manager for Python"
HOMEPAGE = "https://github.com/boto/s3transfer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ce6b5ae5a0f0f580efbcd03f6cfa84c7"

inherit pypi setuptools3
BBCLASSEXTEND = "native"

PYPI_PACKAGE = "s3transfer"

SRC_URI[sha256sum] = "2ed07d3866f523cc561bf4a00fc5535827981b117dd7876f036b0c1aca42c947"

DEPENDS += "python3-botocore"

RDEPENDS:${PN} += "python3-botocore"
