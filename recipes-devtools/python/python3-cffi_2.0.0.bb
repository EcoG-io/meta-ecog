SUMMARY = "Foreign Function Interface for Python calling C code"
HOMEPAGE = "http://cffi.readthedocs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c0158ab9b75875f3bb7fea081d388818"

DEPENDS += "libffi python3-pycparser"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "44d1b5909021139fe36001ae048dbdde8214afa20200eda0f64c068cac5d5529"

# cffi 2.0.0 uses PEP 639 license string; older setuptools expects dict form
do_configure:prepend() {
    if [ -f ${S}/pyproject.toml ]; then
        sed -i 's|^license = "\(.*\)"|license = {text = "\1"}|' ${S}/pyproject.toml
        sed -i '/^license-files/d' ${S}/pyproject.toml
    fi
}

RDEPENDS:${PN} = " \
    python3-ctypes \
    python3-io \
    python3-pycparser \
    python3-setuptools \
    python3-shell \
"

BBCLASSEXTEND = "native nativesdk"

