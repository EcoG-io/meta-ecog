SUMMARY = "Python wrapper module around the OpenSSL library"
HOMEPAGE = "https://pyopenssl.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "8d031884482e0c67ee92bf9a4d8cceb08d92aba7136432ffb0703c5280fc205b"

do_configure:prepend() {
    if [ -f ${S}/pyproject.toml ]; then
        sed -i 's|^license = "\(.*\)"|license = {text = "\1"}|' ${S}/pyproject.toml
        sed -i '/^license-files/d' ${S}/pyproject.toml
    fi
}

RDEPENDS:${PN} += " \
    python3-cryptography \
    python3-six \
"

