SUMMARY = "Python implementation of the OCPP protocol"
HOMEPAGE = "https://github.com/mobilityhouse/ocpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=357da596bd4bc9004894e2feaaa1e975"

SRC_URI = "git://github.com/mobilityhouse/ocpp.git;protocol=https;branch=master"
SRCREV = "ae716a1507d708d83743d1132587cbb03522cda7"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit python_poetry_core

# pyproject.toml requires poetry-core>=2.0.0 but Yocto ships 1.9.0 which can
# build this package fine — relax the constraint to >=1.2.0.
do_configure:prepend() {
    sed -i \
        -e 's|requires = \["poetry-core.*|requires = ["poetry-core>=1.2.0"]|' \
        ${S}/pyproject.toml
}

RDEPENDS:${PN} += "python3-websockets"

